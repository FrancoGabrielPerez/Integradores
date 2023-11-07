package com.microadministration.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * NewScooterDTO
 * 
 * DTO que contiene los atributos de una nueva scooter.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Data
@RequiredArgsConstructor
public class NewScooterDTO {
	private String latitud;
	private String longitud;
    private String estado;
    private double kilometros;
    private int tiempoDeUso;
    private int tiempoEnpausa;

	public NewScooterDTO(String latitud, String longitud,String estado, double kilometros, int tiempoDeUso, int tiempoEnPausa) {
		this.latitud = latitud;
		this.longitud = longitud;
		this.estado = estado;
		this.kilometros = kilometros;
		this.tiempoDeUso = tiempoDeUso;
		this.tiempoEnpausa = tiempoEnPausa;		
	}
}