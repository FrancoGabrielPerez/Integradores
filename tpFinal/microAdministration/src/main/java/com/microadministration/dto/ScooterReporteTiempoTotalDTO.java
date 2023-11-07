package com.microadministration.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ScooterReporteTiempoTotalDTO
 * 
 * DTO para devolver el reporte de uso por tiempo total.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
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