package com.microscooter.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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