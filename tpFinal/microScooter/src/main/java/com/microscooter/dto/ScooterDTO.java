package com.microscooter.dto;

import com.microscooter.model.Scooter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ScooterDTO
 * 
 * DTO que contiene los atributos de un monopatin.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Getter
@RequiredArgsConstructor
public class ScooterDTO {
    private long scooterId;
	private String latitud;
	private String longitud;
    private String estado;
    private double kilometros;
    private int tiempoDeUso;
    private int tiempoEnpausa;

	public ScooterDTO(Scooter scooter) {
		this.scooterId = scooter.getScooterId();
		this.latitud = scooter.getLatitud();
		this.longitud = scooter.getLongitud();
		this.estado = scooter.getEstado();
		this.kilometros = scooter.getKilometros();
		this.tiempoDeUso = scooter.getTiempoDeUso();
		this.tiempoEnpausa = scooter.getTiempoEnpausa();
	}

	public ScooterDTO(String latitud, String longitud,String estado, double kilometros, int tiempoDeUso, int tiempoEnPausa) {
		this.latitud = latitud;
		this.longitud = longitud;
		this.estado = estado;
		this.kilometros = kilometros;
		this.tiempoDeUso = tiempoDeUso;
		this.tiempoEnpausa = tiempoEnPausa;		
	}
}