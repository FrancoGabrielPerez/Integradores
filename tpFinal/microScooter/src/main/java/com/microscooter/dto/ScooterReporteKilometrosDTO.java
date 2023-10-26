package com.microscooter.dto;

import com.microscooter.model.Scooter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScooterReporteKilometrosDTO {
    private long scooterId;
    private String estado;
    private double kilometros;

    public ScooterReporteKilometrosDTO(Scooter scooter) {
        this.scooterId = scooter.getScooterId();
        this.estado = scooter.getEstado();
        this.kilometros = scooter.getKilometros();
    }

    public ScooterReporteKilometrosDTO(String estado, double kilometros) {
        this.estado = estado;
        this.kilometros = kilometros;
    }

}
