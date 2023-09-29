package com.integrador3.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "estudiante_carrera")
@Data
@IdClass(EstudianteCarreraPK.class)
public class EstudianteCarrera {
	@Column(name = "fecha_insc")
	private Timestamp fechaInscripcion;
	@Column(name = "fecha_grad")
	private Timestamp fechaGraduacion;

	@Id
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estudiante_id")
	private Estudiante estudiante;

	@Id
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carrera_id")
	private Carrera carrera;

	public EstudianteCarrera(){
		super();
	}

	public EstudianteCarrera(Estudiante estudiante, Carrera carrera, Timestamp fechaInscripcion, Timestamp fechaGraduado) {
		this.estudiante = Objects.requireNonNull(estudiante, "Estudiante must not be null");
		this.carrera = Objects.requireNonNull(carrera, "Carrera must not be null");
		this.fechaInscripcion = fechaInscripcion;
		this.fechaGraduacion = fechaGraduado;
	}
}