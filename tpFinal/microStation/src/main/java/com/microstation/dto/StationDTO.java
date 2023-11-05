package com.microstation.dto;

import com.microstation.model.StationMongo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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