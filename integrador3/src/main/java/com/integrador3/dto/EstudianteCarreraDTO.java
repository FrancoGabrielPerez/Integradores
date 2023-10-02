package com.integrador3.dto;

import java.sql.Timestamp;
import java.util.Calendar;

import com.integrador3.model.EstudianteCarrera;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EstudianteCarreraDTO {
	private Timestamp fechaInscripcion;
	private Timestamp fechaGraduacion;
	private String e;
	private String c;

	public EstudianteCarreraDTO(EstudianteCarrera estudianteCarrera) {
		this.fechaGraduacion = estudianteCarrera.getFechaGraduacion();
		this.fechaInscripcion = estudianteCarrera.getFechaInscripcion();
		this.e = estudianteCarrera.getEstudiante().getNombre() + " " + estudianteCarrera.getEstudiante().getApellido();
		this.c = estudianteCarrera.getCarrera().getNombre();
	}

	// TODO eliminar si no se usa
	public EstudianteCarreraDTO(String e, String c, Timestamp fechaInscripcion, Timestamp fechaGaduacion) {
		this.fechaInscripcion = fechaInscripcion;
		this.fechaGraduacion = fechaGaduacion;
		this.e = e;
		this.c = c;
	}

	public Integer getAntiguedad(){
		Calendar fechaInscripcion = Calendar.getInstance();
		fechaInscripcion.setTimeInMillis(this.fechaInscripcion.getTime());
		return Calendar.getInstance().get(Calendar.YEAR) - fechaInscripcion.get(Calendar.YEAR);
	}
	
}