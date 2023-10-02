package com.integrador3.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String nombre;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
	private Set<EstudianteCarrera> estudiantes;

	public Carrera() {
		super();
		this.estudiantes = new HashSet<>();
	}

	public Carrera(String nombre) {
		this.nombre = nombre;
		this.estudiantes = new HashSet<>();
	}
}