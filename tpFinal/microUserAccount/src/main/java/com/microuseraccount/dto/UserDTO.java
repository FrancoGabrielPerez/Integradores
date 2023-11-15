package com.microuseraccount.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.microuseraccount.model.User;

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
	private String password;

	public UserDTO(User user) {
		this.userId = user.getUserId();
		this.nombre = user.getNombre();
		this.apellido = user.getApellido();
		this.nroCelular = user.getNroCelular();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}

	public UserDTO(long userId, String nombre, String apellido, long nroCelular, String email, String password) {
		this.userId = userId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
		this.password = password;
	}
}