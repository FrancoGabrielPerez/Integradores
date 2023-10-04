package com.integrador3.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class InformeCarreraDTO {
	private String carrera;
	private Integer año;
	private Long inscriptos;
	private Long graduados;

	public InformeCarreraDTO() {}

	public InformeCarreraDTO(Object[] o) {
		this.carrera = (String) o[0];
		this.año = (Integer) o[1];
		this.inscriptos = ((BigDecimal) o[2]).longValue();
		this.graduados = ((BigDecimal) o[3]).longValue();
	}
	
	public InformeCarreraDTO(String carrera, Integer año, Long inscriptos, Long graduados) {
		this.carrera = carrera;
		this.año = año;
		this.inscriptos = inscriptos;
		this.graduados = graduados;
	}
}
