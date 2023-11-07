package com.microadministration.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ScooterReporteKilometrosDTO
 * 
 * DTO para devolver un reporte de kilometros recorridos.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Getter
@RequiredArgsConstructor
public class ScooterReporteKilometrosDTO {
    private long scooterId;
    private String estado;
    private double kilometros;

    public ScooterReporteKilometrosDTO(ScooterDTO scooter) {
        this.scooterId = scooter.getScooterId();
        this.estado = scooter.getEstado();
        this.kilometros = scooter.getKilometros();
    }

    public ScooterReporteKilometrosDTO(String estado, double kilometros) {
        this.estado = estado;
        this.kilometros = kilometros;
    }

}
