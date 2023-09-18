package entities;

import java.sql.Timestamp;
import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Estudiante_Carrera {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	// @ManyToOne
    // @Id
	// private Estudiante estudiante;
	// @ManyToOne
    // @Id
	// private Carrera carrera;
    @Column(name = "fecha_insc")
	private Timestamp fechaInscripcion;
    @Column(name = "esta_graduado")
	private boolean graduado;	

	@ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

	public Estudiante_Carrera(){
		super();
	}

	public Estudiante_Carrera(Estudiante estudiante, Carrera carrera, Timestamp fechaInscripcion, boolean esGraduado) {
		this.estudiante = estudiante;
		this.carrera = carrera;
        this.fechaInscripcion = fechaInscripcion;
        this.graduado = esGraduado;
	}

	public Estudiante_Carrera(Estudiante estudiante, Carrera carrera, Timestamp fechaInscripcion) {
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

	@Override
	public String toString() { //TODO sacar ids
		return "Estudiante_Carrera [id_estudiante=" + estudiante.getId() + ", id_carrera=" + carrera.getId()
				+ ", fechaInscripcion=" + fechaInscripcion + ", graduado=" + graduado + "]";
	}

	
}