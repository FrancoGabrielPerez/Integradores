package com.microadministration.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.microadministration.model.AdminStaff;

@Getter
@RequiredArgsConstructor
public class AdminStaffDTO {
	private String nombre;
	private String apellido;
	private long nroCelular;
	private String email;
	private String rol;
	private String password;

	public AdminStaffDTO(AdminStaff admin) {
		this.nombre = admin.getNombre();
		this.apellido = admin.getApellido();
		this.nroCelular = admin.getNroCelular();
		this.email = admin.getEmail();
		this.rol = admin.getRol();
		this.password = admin.getPassword();
	}

	public AdminStaffDTO(String rol, String nombre, String apellido, long nroCelular, String email, String password) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
		this.rol = rol;
		this.password = password;
	}

	public AdminStaffDTO(AdminStaffDTO dto) {
		this.nombre = dto.getNombre();
		this.apellido = dto.getApellido();
		this.nroCelular = dto.getNroCelular();
		this.email = dto.getEmail();
		this.rol = dto.getRol();
		this.password = dto.getPassword();
	}
}