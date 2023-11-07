package com.microadministration.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

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