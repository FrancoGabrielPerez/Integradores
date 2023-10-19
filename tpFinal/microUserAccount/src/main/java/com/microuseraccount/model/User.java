package com.microuseraccount.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

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
	@Column(name="email")
	private String email;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="user_id")
	private Integer userId;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserAccount> cuentas;

	public User(){
		super();
		this.cuentas = new HashSet<>();
	}

	public User(String nombre, String apellido, long nroCelular, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCelular = nroCelular;
		this.email = email;
		this.cuentas = new HashSet<>();
	}

//	public User(EstudianteDTO dto){
//		this.nombre = dto.getNombre();
//		this.apellido = dto.getApellido();
//		this.ciudadResidencia = dto.getCiudadResidencia();
//		this.genero = dto.getGenero();
//		this.dni = dto.getDni();
//		this.id = dto.getLibreta();
//		this.edad = dto.getEdad();
//		this.carreras = new HashSet<>();
//	}
}