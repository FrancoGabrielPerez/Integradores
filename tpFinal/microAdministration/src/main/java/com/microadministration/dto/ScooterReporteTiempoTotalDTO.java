package com.microadministration.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScooterReporteTiempoTotalDTO {
    private long scooterId;
    private int tiempoTotal;
    private String estado;

    public ScooterReporteTiempoTotalDTO(String estado, int tiempoDeUso, int tiempoEnPausa) {
        this.estado = estado;
        this.tiempoTotal = tiempoDeUso + tiempoEnPausa;
    }

}