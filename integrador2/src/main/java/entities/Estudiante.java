package entities;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(indexes = @Index(columnList = "dni, libreta"))
public class Estudiante{
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="ciudad_residencia")
    private String ciudad_residencia;
    @Column(name="genero")
    private String genero;
    @Column(name="dni")
	private Integer dni;
    @Column(name="libreta")
	private Integer libreta;
	@Column(name="carrera")
	@OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
	private List<Estudiante_Carrera> carreras;

    public Estudiante(){
        super();
    }

    public Estudiante(String nombre, String apellido, String ciudad_residencia, String genero) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.ciudad_residencia = ciudad_residencia;
		this.genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCiudad_residencia() {
		return ciudad_residencia;
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
		this.ciudad_residencia = ciudad_residencia;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<Estudiante_Carrera> getCarreras() {
		return new LinkedList<>(carreras);
	}

	@Override
	public String toString() { //TODO sacar ids
		return "Estudiante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", ciudad_residencia="
				+ ciudad_residencia + ", genero=" + genero + "]";
	}

}