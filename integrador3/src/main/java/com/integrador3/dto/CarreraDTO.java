package com.integrador3.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.integrador3.model.Carrera;

@Getter
@RequiredArgsConstructor
public class CarreraDTO {
	private Integer id;
	private String nombre;

	public CarreraDTO(Carrera carrera) {
		this.id = carrera.getId();
		this.nombre = carrera.getNombre();
	}
}
