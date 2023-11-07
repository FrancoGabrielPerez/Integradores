package com.microadministration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.microadministration.dto.AdminStaffDTO;

/**
 * AdminStaff
 * 
 * Clase que contiene los atributos de los integrantes del staff de administracion.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
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
	@Column(name="password")
	private String password;

	private Set<Authority> authorities;

	public AdminStaff(){
		super();
	}

	public AdminStaff(String rol, String nombre, String apellido, long nroCelular, String email, String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;	
		this.rol = rol;
		this.password = password;
	}

	public AdminStaff(AdminStaffDTO dto){
		this.adminId = dto.getId();
		this.nombre = dto.getNombre();
		this.apellido = dto.getApellido();
		this.nroCelular = dto.getNroCelular();
		this.email = dto.getEmail();
		this.rol = dto.getRol();
		this.password = dto.getPassword();
	}

    public void setAuthorities( Collection<Authority> authorities ){
        this.authorities = new HashSet<>( authorities );
    }
}

