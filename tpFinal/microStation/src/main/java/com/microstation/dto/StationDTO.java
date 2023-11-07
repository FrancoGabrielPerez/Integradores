package com.microstation.dto;

import com.microstation.model.StationMongo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * StationDTO
 * 
 * Clase que contiene los atributos de una estacion.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 *  
 */
@Getter
@RequiredArgsConstructor
public class StationDTO {
	private String latitud;
	private String longitud;
	private String name;
	private String id;
	
	public StationDTO(StationMongo station) {
		this.id = station.getId();
		this.name = station.getName();
		this.latitud = station.getLatitud();
		this.longitud = station.getLongitud();
	}

	public StationDTO(String id, String name, String latitud, String longitud) {
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.name = name;
	}
}