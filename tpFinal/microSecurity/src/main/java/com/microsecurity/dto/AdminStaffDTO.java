package com.microsecurity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microsecurity.model.AdminStaff;

/**
 * AdminStaffDTO
 * 
 * DTO que contiene los atributos de un integrante del staff de administracion.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Data
//@NoArgsConstructor
@JsonIgnoreProperties( ignoreUnknown = true )
public class AdminStaffDTO {
	private String nombre;
	private String apellido;
	private long nroCelular;
	private String email;
	private String password;
	private long id;
	private Set<String> authorities;

	public AdminStaffDTO(AdminStaff admin) {
		this.id = admin.getAdminId();
		this.nombre = admin.getNombre();
		this.apellido = admin.getApellido();
		this.nroCelular = admin.getNroCelular();
		this.email = admin.getEmail();
		this.password = admin.getPassword();
	}

	public AdminStaffDTO(Long id, String nombre, String apellido, long nroCelular, String email, String password) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
		this.password = password;
	}

	public AdminStaffDTO(AdminStaffDTO dto) {
		this.id = dto.getId();
		this.nombre = dto.getNombre();
		this.apellido = dto.getApellido();
		this.nroCelular = dto.getNroCelular();
		this.email = dto.getEmail();
		this.password = dto.getPassword();
	}
}