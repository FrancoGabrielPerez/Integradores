package com.microscooter.dto;

import com.microscooter.model.Scooter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ScooterReporteKmsTiempoUsoDTO
 * 
 * DTO que contiene los atributos para devolver un reporte de monopatines por kilometros y tiempo de uso.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Getter
@RequiredArgsConstructor
public class ScooterReporteKmsTiempoUsoDTO {
    private long scooterId;
    private double kilometros;
    private int tiempoUso;

    public ScooterReporteKmsTiempoUsoDTO(Scooter scooter) {
        this.scooterId = scooter.getScooterId();
        this.kilometros = scooter.getKilometros();
        this.tiempoUso = scooter.getTiempoDeUso();
    }

    public ScooterReporteKmsTiempoUsoDTO(ScooterDTO scooter) {
        this.scooterId = scooter.getScooterId();
        this.kilometros = scooter.getKilometros();
        this.tiempoUso = scooter.getTiempoDeUso();
    }

    public ScooterReporteKmsTiempoUsoDTO(long scooterId, double kilometros, int tiempoUso) {
        this.scooterId = scooterId;
        this.kilometros = kilometros;
        this.tiempoUso = tiempoUso;
    }

}
