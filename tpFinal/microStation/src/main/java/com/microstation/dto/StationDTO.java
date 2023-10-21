package com.microstation.dto;

import com.microstation.model.Station;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StationDTO {
	private String latitud;
	private String longitud;

	public StationDTO(Station station) {
		this.latitud = station.getLatitud();
		this.longitud = station.getLongitud();
	}

	public StationDTO(String latitud, String longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
}