package com.integrador3.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(indexes ={@Index(name = "idx_dni", columnList = "dni")})
@Data
public class Estudiante{
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="edad")
	private int edad;
	@Column(name="ciudad_residencia")
	private String ciudadResidencia;
	@Column(name="genero")
	private String genero;
	@Column(name="dni")
	private Integer dni;
	@Id
	@Column(name="libreta")
	private Integer id;
	@Column(name="carrera")
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<EstudianteCarrera> carreras;

	public Estudiante(){
		super();
		this.carreras = new HashSet<>();
	}

	public Estudiante(String nombre, String apellido, int edad, String ciudad_residencia, String genero, Integer dni, Integer libreta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.ciudadResidencia = ciudad_residencia;
		this.genero = genero;
		this.dni = dni;
		this.id = libreta;
		this.edad = edad;
		this.carreras = new HashSet<>();
	}
}