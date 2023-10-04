package com.integrador3.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.integrador3.dto.CarreraDTO;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Carrera(CarreraDTO dto) {
		this.nombre = dto.getNombre();
		this.estudiantes = new HashSet<>();
	}
}