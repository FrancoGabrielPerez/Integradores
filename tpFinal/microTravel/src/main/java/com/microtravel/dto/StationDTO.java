package com.microtravel.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StationDTO {
	private String latitud;
	private String longitud;

	public StationDTO(String latitud, String longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
}