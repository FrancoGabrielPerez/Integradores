package com.microscooter.dto;

import lombok.Getter;

@Getter
public class StationDTO {
	private String latitud;
	private String longitud;

	public StationDTO(){

	}

	public StationDTO(String latitud, String longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
}