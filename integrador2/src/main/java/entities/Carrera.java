package entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Carrera {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String nombre;
    @ManyToMany
    private List<Estudiante> estudiantes;
   
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
	@Override
	public String toString() {
		return "Carrera [id=" + id + ", nombre=" + nombre + "]";
	}
}