package com.microadministration.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * StationDTO
 * 
 * DTO que contiene los atributos de una estacion.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Data
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