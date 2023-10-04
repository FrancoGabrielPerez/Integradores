package com.integrador3.dto;

import org.springframework.data.relational.core.sql.In;

import com.integrador3.model.Carrera;

import lombok.Getter;

@Getter
public class InformeCarreraCantEstudiantesDTO {
	private String nombre;
	private Long cantEstudiantes;

	public InformeCarreraCantEstudiantesDTO() {}
	
	public InformeCarreraCantEstudiantesDTO(String nombre, Long cantEstudiantes) {
		this.nombre = nombre;
		this.cantEstudiantes = cantEstudiantes;
	}

	public InformeCarreraCantEstudiantesDTO(Carrera carrera) {
		this.nombre = carrera.getNombre();
		this.cantEstudiantes = (long) carrera.getEstudiantes().size();
	}

	public InformeCarreraCantEstudiantesDTO(Object[] objects) {
		this.nombre = (String) objects[0];
		this.cantEstudiantes = (Long) objects[1];
	}
}