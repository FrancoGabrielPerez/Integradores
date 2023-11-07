package com.microscooter.dto;

import com.microscooter.model.Scooter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ScooterReporteTiempoTotalDTO
 * 
 * DTO que contiene los atributos necesarios para devolver un reporte de tiempo total de uso de un monopatin.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
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