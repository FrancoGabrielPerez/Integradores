package com.microscooter.dto;

import com.microscooter.model.Scooter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScooterReporteTiempoTotalDTO {
    private long scooterId;
    private int tiempoTotal;
    private String estado;

    public ScooterReporteTiempoTotalDTO(Scooter scooter) {
        this.scooterId = scooter.getScooterId();
        this.estado = scooter.getEstado();
        this.tiempoTotal = scooter.getTiempoDeUso() + scooter.getTiempoEnpausa();
    }

    public ScooterReporteTiempoTotalDTO(String estado, int tiempoDeUso, int tiempoEnPausa) {
        this.estado = estado;
        this.tiempoTotal = tiempoDeUso + tiempoEnPausa;
    }

}