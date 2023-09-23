package entity;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
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

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<EstudianteCarrera> getEstudiantes() {
		return new LinkedList<>(estudiantes);
	}

	public void setEstudiantes(EstudianteCarrera estudiante) {
		this.estudiantes.add(estudiante);
	}
}