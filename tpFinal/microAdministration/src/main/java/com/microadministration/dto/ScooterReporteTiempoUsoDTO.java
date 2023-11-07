package com.microadministration.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ScooterReporteTiempoUsoDTO
 * 
 * DTO para devolver el reporte de uso un scooter por tiempo de uso.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Getter
@RequiredArgsConstructor
public class ScooterReporteTiempoUsoDTO {
    private long scooterId;
    private int tiempoDeUso;
    private int tiempoEnpausa;
    private String estado;

    public ScooterReporteTiempoUsoDTO(String estado, int tiempoDeUso, int tiempoEnPausa) {
        this.estado = estado;
        this.tiempoDeUso = tiempoDeUso;
        this.tiempoEnpausa = tiempoEnPausa;
    }

}