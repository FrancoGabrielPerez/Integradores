package dtos;

import java.sql.Timestamp;

public class EstudianteCarreraDTO {
	private Timestamp fechaInscripcion;
	private Timestamp fechaGraduacion;
	private int antiguedad;

	public EstudianteCarreraDTO(Timestamp fechaInscripcion, Timestamp fechaGaduacion, int antiguedad) {
		this.fechaInscripcion = fechaInscripcion;
		this.fechaGraduacion = fechaGaduacion;
		this.antiguedad = antiguedad;
	}

	public Timestamp getFechaInscripcion() {
		return fechaInscripcion;
	}

	public boolean isGraduado() {
		return fechaGraduacion != null;
	}

	public int getAntiguedad() {
		return antiguedad;
	}
	
	@Override
	public String toString() {
		return "EstudianteCarreraDTO [fechaInscripcion=" + fechaInscripcion + ", fechaGraduacion=" + fechaGraduacion + ", antiguedad=" + antiguedad + "]";
	}
	
}
