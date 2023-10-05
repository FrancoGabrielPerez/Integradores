package com.integrador3.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.integrador3.model.Estudiante;

@Getter
@RequiredArgsConstructor
public class EstudianteDTO {
	private String nombre;
	private String apellido;
	private int edad;
	private String ciudadResidencia;
	private String genero;
	private Integer dni;
	private Integer libreta;

	public EstudianteDTO(Estudiante estudiante) {
		this.nombre = estudiante.getNombre();
		this.apellido = estudiante.getApellido();
		this.edad = estudiante.getEdad();
		this.ciudadResidencia = estudiante.getCiudadResidencia();
		this.genero = estudiante.getGenero();
		this.dni = estudiante.getDni();
		this.libreta = estudiante.getId();
	}
	
	public EstudianteDTO(String nombre, String apellido, int edad, String ciudadResidencia, String genero,
			Integer dni, Integer libreta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.ciudadResidencia = ciudadResidencia;
		this.genero = genero;
		this.dni = dni;
		this.libreta = libreta;
	}
}