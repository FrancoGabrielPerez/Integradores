package com.microtravel.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * UserDTO
 * 
 * DTO que contiene los atributos de un usuario.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Getter
@RequiredArgsConstructor
public class UserDTO {
	private long userId;
	private String nombre;
	private String apellido;
	private long nroCelular;
	private String email;

	public UserDTO(long userId, String nombre, String apellido, long nroCelular, String email) {
		this.userId = userId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
	}
}