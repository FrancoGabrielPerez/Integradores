package com.microscooter.dto;

import com.microscooter.model.Scooter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ScooterReporteTiempoUsoDTO
 * 
 * DTO que contiene los atributos necesarios para devolver un reporte de tiempo de uso de un monopatin.
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

    public ScooterReporteTiempoUsoDTO(Scooter scooter) {
        this.scooterId = scooter.getScooterId();
        this.estado = scooter.getEstado();
        this.tiempoDeUso = scooter.getTiempoDeUso();
        this.tiempoEnpausa = scooter.getTiempoEnpausa();
    }

    public ScooterReporteTiempoUsoDTO(String estado, int tiempoDeUso, int tiempoEnPausa) {
        this.estado = estado;
        this.tiempoDeUso = tiempoDeUso;
        this.tiempoEnpausa = tiempoEnPausa;
    }

}