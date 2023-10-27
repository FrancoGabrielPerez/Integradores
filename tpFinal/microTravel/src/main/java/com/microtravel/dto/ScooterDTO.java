package com.microtravel.dto;

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

	public ScooterDTO(String latitud, String longitud, String estado, double kilometros, int tiempoDeUso, int tiempoEnPausa) {
		this.latitud = latitud;
		this.longitud = longitud;
		this.estado = estado;
		this.kilometros = kilometros;
		this.tiempoDeUso = tiempoDeUso;
		this.tiempoEnpausa = tiempoEnPausa;		
	}

	public ScooterDTO(ScooterDTO dto, String estado){
		this.latitud = dto.getLatitud();
		this.longitud = dto.getLongitud();
		this.estado = estado;
		this.kilometros = dto.getKilometros();
		this.tiempoDeUso = dto.getTiempoDeUso();
		this.tiempoEnpausa = dto.getTiempoEnpausa();
	}
}