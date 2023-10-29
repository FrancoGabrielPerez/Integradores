package com.microuseraccount.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.microuseraccount.model.User;

@Getter
@RequiredArgsConstructor
public class UserDTO {
	private long userId;
	private String nombre;
	private String apellido;
	private long nroCelular;
	private String email;;

	public UserDTO(User user) {
		this.userId = user.getUserId();
		this.nombre = user.getNombre();
		this.apellido = user.getApellido();
		this.nroCelular = user.getNroCelular();
		this.email = user.getEmail();
	}

	public UserDTO(long userId, String nombre, String apellido, long nroCelular, String email) {
		this.userId = userId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
	}
}