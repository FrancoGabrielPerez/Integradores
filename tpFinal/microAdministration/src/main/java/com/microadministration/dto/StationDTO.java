package com.microadministration.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StationDTO {
	private String latitud;
	private String longitud;

	public StationDTO(String latitud, String longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
}