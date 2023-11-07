package com.microtravel.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * StationDTO
 * 
 * DTO que contiene los atributos necesarios para devolver una estacion.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Getter
@RequiredArgsConstructor
public class StationDTO {
	private String name;
	private String latitud;
	private String longitud;

	public StationDTO(String name, String latitud, String longitud) {
		this.name = name;
		this.latitud = latitud;
		this.longitud = longitud;
	}
}