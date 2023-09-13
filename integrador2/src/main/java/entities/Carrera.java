package entities;

import java.util.LinkedList;
import java.util.List;

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
    private List<Estudiante_Carrera> estudiantes;
   
	public Carrera() {
        super();
	}

	public Carrera(String nombre) {
		this.nombre = nombre;
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

	public List<Estudiante_Carrera> getEstudiantes() {
		return new LinkedList<>(estudiantes);
	}
	
	@Override
	public String toString() { //TODO sacar ids
		return "Carrera [id=" + id + ", nombre=" + nombre + "]";
	}
}