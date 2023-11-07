package com.microscooter.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * InformeEstadoMonopatinesDTO
 * 
 * Clase que contiene los atributos de un informe de estado de monopatines.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Getter
@RequiredArgsConstructor
public class InformeEstadoMonopatinesDTO {
    private int enMantenimiento;
	private int operativos;

	public InformeEstadoMonopatinesDTO(int enMantenimiento, int operativos) {
		this.enMantenimiento = enMantenimiento;
		this.operativos = operativos;
	}
}