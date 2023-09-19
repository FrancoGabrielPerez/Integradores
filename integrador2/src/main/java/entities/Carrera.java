package entities;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
	@OneToMany(mappedBy = "carrera")
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
	
	@Override
	public String toString() { //TODO sacar ids
		return "Carrera [id=" + id + ", nombre=" + nombre + "]";
	}
}