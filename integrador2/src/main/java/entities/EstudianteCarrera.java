package entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante_carrera")
public class EstudianteCarrera {
	@Column(name = "fecha_insc")
	private Timestamp fechaInscripcion;
	@Column(name = "fecha_grad")
	private Timestamp fechaGraduado;

	@Id
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "estudiante_id")
	private Estudiante estudiante;

	@Id
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "carrera_id")
	private Carrera carrera;

	public EstudianteCarrera(){
		super();
	}

	public EstudianteCarrera(Estudiante estudiante, Carrera carrera, Timestamp fechaInscripcion, Timestamp fechaGraduado) {
		this.estudiante = Objects.requireNonNull(estudiante, "Estudiante must not be null");
		this.carrera = Objects.requireNonNull(carrera, "Carrera must not be null");
		this.fechaInscripcion = fechaInscripcion;
		this.fechaGraduado = fechaGraduado;
	}

	public EstudianteCarrera(Estudiante estudiante, Carrera carrera, Timestamp fechaInscripcion) {
		this(estudiante, carrera, fechaInscripcion, null);
	}

	public Integer getLibreta() {
		return estudiante.getLibreta();
	}

	public Integer getId_carrera() {
		return carrera.getId();
	}

	public void setGraduado(Timestamp fechaGraduado) {
		this.fechaGraduado = fechaGraduado;
	}

	public Integer getAntiguedad(){
		Calendar fechaInscripcion = Calendar.getInstance();
		fechaInscripcion.setTimeInMillis(this.fechaInscripcion.getTime());
		return Calendar.getInstance().get(Calendar.YEAR) - fechaInscripcion.get(Calendar.YEAR);
	}
}