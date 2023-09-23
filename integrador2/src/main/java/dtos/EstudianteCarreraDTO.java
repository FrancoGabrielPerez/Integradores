package dtos;

import java.sql.Timestamp;
import java.util.Calendar;

public class EstudianteCarreraDTO {
	private Timestamp fechaInscripcion;
	private Timestamp fechaGraduacion;
	private String e;
	private String c;

	public EstudianteCarreraDTO(String e, String c, Timestamp fechaInscripcion, Timestamp fechaGaduacion) {
		this.fechaInscripcion = fechaInscripcion;
		this.fechaGraduacion = fechaGaduacion;
		this.e = e;
		this.c = c;
	}

	public Timestamp getFechaInscripcion() {
		return fechaInscripcion;
	}

	public boolean isGraduado() {
		return fechaGraduacion != null;
	}

	public Integer getAntiguedad(){
		Calendar fechaInscripcion = Calendar.getInstance();
		fechaInscripcion.setTimeInMillis(this.fechaInscripcion.getTime());
		return Calendar.getInstance().get(Calendar.YEAR) - fechaInscripcion.get(Calendar.YEAR);
	}
	
	public Timestamp getFechaGraduacion() {
		return fechaGraduacion;
	}

	public String getE() {
		return e;
	}

	public String getC() {
		return c;
	}

	@Override
	public String toString() {
		return "Estudiante: " + e + ", Carrera: " + c +", fecha de inscripcion: " + fechaInscripcion + ", fecha de graduacion:" + fechaGraduacion + ", antiguedad:" + getAntiguedad();
	}
}