package entities;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(indexes ={@Index(name = "idx_dni", columnList = "dni")})
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
	private Integer libreta;
	@Column(name="carrera")
	@OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
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
		this.libreta = libreta;
		this.edad = edad;
		this.carreras = new HashSet<>();
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Integer getLibreta() {
		return libreta;
	}

	public void setLibreta(Integer libreta) {
		this.libreta = libreta;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCiudad_residencia() {
		return ciudadResidencia;
	}

	public String getGenero() {
		return genero;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCiudad_residencia(String ciudad_residencia) {
		this.ciudadResidencia = ciudad_residencia;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<EstudianteCarrera> getCarreras() {
		return new LinkedList<>(carreras);
	}

	public void setCarreras(EstudianteCarrera carrera) {
		this.carreras.add(carrera);
	}
}