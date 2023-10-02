package com.integrador3.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.integrador3.model.Carrera;

@Getter
@RequiredArgsConstructor
public class CarreraDTO {
	private String nombre;

	public CarreraDTO(Carrera carrera) {
		this.nombre = carrera.getNombre();
	}
	
	public CarreraDTO(String nombre) {
		this.nombre = nombre;
	}
}
