package com.microadministration.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.microadministration.dto.AdminStaffDTO;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "staff")
public class AdminStaff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="admin_id")
	private long adminId;

	@Column(name="rol")
	private String rol;
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="nro_celular")
	private long nroCelular;
	@Column(name="email")
	private String email;


	public AdminStaff(){
		super();
	}

	public AdminStaff(String rol, String nombre, String apellido, long nroCelular, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;	
		this.rol = rol;
	}

	public AdminStaff(AdminStaffDTO dto){
		this.nombre = dto.getNombre();
		this.apellido = dto.getApellido();
		this.nroCelular = dto.getNroCelular();
		this.email = dto.getEmail();
		this.rol = dto.getRol();
	}
}