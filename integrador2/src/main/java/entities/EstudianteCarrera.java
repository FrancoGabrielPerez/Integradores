package entities;

import java.sql.Timestamp;
import java.util.Calendar;

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
    @Column(name = "esta_graduado")
	private boolean graduado;	

	@Id
	@ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

	@Id
    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

	public EstudianteCarrera(){
		super();
	}

	public EstudianteCarrera(Estudiante estudiante, Carrera carrera, Timestamp fechaInscripcion, boolean esGraduado) {
		this.estudiante = estudiante;
		this.carrera = carrera;
        this.fechaInscripcion = fechaInscripcion;
        this.graduado = esGraduado;
		// estudiante.setCarreras(this);
        // carrera.setEstudiantes(this);
	}

	public EstudianteCarrera(Estudiante estudiante, Carrera carrera, Timestamp fechaInscripcion) {
		this(estudiante, carrera, fechaInscripcion, false);
	}

	public Integer getId_estudiante() {
		return estudiante.getId();
	}

	public Integer getId_carrera() {
		return carrera.getId();
	}

	public Boolean setGraduado(Boolean graduado) {
		return this.graduado = graduado;
	}

	public Integer getAntiguedad(){
		Calendar fechaInscripcion = Calendar.getInstance();
		fechaInscripcion.setTimeInMillis(this.fechaInscripcion.getTime());
		return Calendar.getInstance().get(Calendar.YEAR) - fechaInscripcion.get(Calendar.YEAR);
	}
}