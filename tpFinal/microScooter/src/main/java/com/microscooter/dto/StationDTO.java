package com.microscooter.dto;

import lombok.Getter;

/**
 * StationDTO
 * 
 * DTO que contiene los atributos necesarios para devolver una estacion.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Getter
public class StationDTO {
	private String latitud;
	private String longitud;
	private String name;

	public StationDTO(){
		super();
	}

	public StationDTO(String name, String latitud, String longitud) {
		this.name = name;
		this.latitud = latitud;
		this.longitud = longitud;
	}
}