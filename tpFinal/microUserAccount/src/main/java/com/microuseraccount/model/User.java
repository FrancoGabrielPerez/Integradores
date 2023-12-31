package com.microuseraccount.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.microuseraccount.dto.UserDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * User
 * 
 * Clase que contiene los atributos de un usuario.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Entity
@Data
@Table(name = "user")
public class User {
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="nro_celular")
	private long nroCelular;
	@Column(name="email", nullable = false, unique = true)
	private String email;

	@Column(name="password")
	@Transient
	private String password;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private long userId;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<UserAccount> cuentas;

	public User(){
		super();
		this.cuentas = new HashSet<>();
	}

	public User(String nombre, String apellido, long nroCelular, String email, String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;		
		this.password = password;
		this.cuentas = new HashSet<>();
	}

	public User(UserDTO dto){
		this.nombre = dto.getNombre();
		this.apellido = dto.getApellido();
		this.nroCelular = dto.getNroCelular();
		this.email = dto.getEmail();
		this.password = dto.getPassword();
		this.cuentas = new HashSet<>();
	}
}