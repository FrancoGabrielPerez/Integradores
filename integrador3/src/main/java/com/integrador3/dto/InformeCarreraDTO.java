package com.integrador3.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class InformeCarreraDTO {
	private String carrera;
	private Integer año;
	private int inscriptos;
	private int graduados;

	public InformeCarreraDTO() {}
	
	public InformeCarreraDTO(String carrera, Integer año, BigDecimal inscriptos, BigDecimal graduados) {
		this.carrera = carrera;
		this.año = año;
		this.inscriptos = inscriptos.intValue();
		this.graduados = graduados.intValue();
	}
}
