package com.microscooter.model;

import com.microscooter.dto.ScooterDTO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

/**
 * Scooter
 * 
 * Clase que contiene los atributos de un monopatin.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Entity
@Data
@Table(name = "scooter")
public class Scooter{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="scooter_id")
	private long scooterId;

	@Column(name = "latitud")
	private String latitud;

	@Column(name = "longitud")
	private String longitud;

    @Column(name = "estado")
    private String estado;

    @Column(name = "kilometros")
    private double kilometros;

    @Column(name = "tiempo_de_uso")
    private int tiempoDeUso;

    @Column(name = "tiempo_en_pausa")
    private int tiempoEnpausa;

    public Scooter(){
        super();
		this.estado = "Libre";
		this.kilometros = 0;
		this.tiempoDeUso = 0;
		this.tiempoEnpausa = 0;
		this.latitud = "0";
		this.longitud = "0";
    }

    public Scooter(@NonNull ScooterDTO dto){ //dejamos el notnull?
		super();
		this.latitud = dto.getLatitud();
		this.longitud = dto.getLongitud();
		this.estado = dto.getEstado();
		this.kilometros = dto.getKilometros();
		this.tiempoDeUso = dto.getTiempoDeUso();
		this.tiempoEnpausa = dto.getTiempoEnpausa();
    }

	public void setFromDTO(@NonNull ScooterDTO dto){
		this.latitud = dto.getLatitud();
		this.longitud = dto.getLongitud();
		this.estado = dto.getEstado();
		this.kilometros = dto.getKilometros();
		this.tiempoDeUso = dto.getTiempoDeUso();
		this.tiempoEnpausa = dto.getTiempoEnpausa();
	}

	public Scooter(@NonNull String latitud, @NonNull String longitud){
		super();
		this.latitud = latitud;
		this.longitud = longitud;
		this.estado = "disponible";
		this.kilometros = 0;
		this.tiempoDeUso = 0;
		this.tiempoEnpausa = 0;
	}

	public double calcularDistancia(double latitudDestino, double longitudDestino) {
        int radioTierra = 6371; // Radio de la Tierra en kilómetros

        double dLat = Math.toRadians(latitudDestino - Double.parseDouble(latitud));
        double dLon = Math.toRadians(longitudDestino - Double.parseDouble(longitud));

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(Double.parseDouble(latitud))) * Math.cos(Math.toRadians(latitudDestino)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radioTierra * c; // Distancia en kilómetros
    }
}