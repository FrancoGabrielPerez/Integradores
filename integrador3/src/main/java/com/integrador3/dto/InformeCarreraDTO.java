package com.integrador3.dto;

import lombok.Getter;

@Getter
public class InformeCarreraDTO {
	private String carrera;
	private Integer año;
	private Long inscriptos;
	private Long graduados;

	public InformeCarreraDTO() {}
	
	public InformeCarreraDTO(String carrera, Integer año, Long inscriptos, Long graduados) {
		this.carrera = carrera;
		this.año = año;
		this.inscriptos = inscriptos;
		this.graduados = graduados;
	}
}
