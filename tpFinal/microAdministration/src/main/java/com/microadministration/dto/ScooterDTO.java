package com.microadministration.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ScooterDTO {
	private long scooterId;
	private String latitud;
	private String longitud;
    private String estado;
    private double kilometros;
    private int tiempoDeUso;
    private int tiempoEnpausa;

	public ScooterDTO(long scooterId,String latitud, String longitud,String estado, double kilometros, int tiempoDeUso, int tiempoEnPausa) {
		this.scooterId = scooterId;
		this.latitud = latitud;
		this.longitud = longitud;
		this.estado = estado;
		this.kilometros = kilometros;
		this.tiempoDeUso = tiempoDeUso;
		this.tiempoEnpausa = tiempoEnPausa;		
	}
}