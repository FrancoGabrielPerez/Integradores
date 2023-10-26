package com.microadministration.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.microadministration.model.AdminStaff;

@Getter
@RequiredArgsConstructor
public class AdminStaffDTO {
	private long adminId;
	private String nombre;
	private String apellido;
	private long nroCelular;
	private String email;
	private String rol;

	public AdminStaffDTO(AdminStaff admin) {
		this.adminId = admin.getAdminId();
		this.nombre = admin.getNombre();
		this.apellido = admin.getApellido();
		this.nroCelular = admin.getNroCelular();
		this.email = admin.getEmail();
		this.rol = admin.getRol();
	}

	public AdminStaffDTO(String rol, long adminId, String nombre, String apellido, long nroCelular, String email) {
		this.adminId = adminId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
		this.rol = rol;
	}

	public AdminStaffDTO(AdminStaffDTO dto) {
		this.nombre = dto.getNombre();
		this.apellido = dto.getApellido();
		this.nroCelular = dto.getNroCelular();
		this.email = dto.getEmail();
		this.rol = dto.getRol();
	}
}