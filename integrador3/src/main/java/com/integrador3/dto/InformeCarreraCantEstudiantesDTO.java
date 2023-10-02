package com.integrador3.dto;

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
}