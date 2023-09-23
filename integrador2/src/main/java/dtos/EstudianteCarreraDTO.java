package dtos;

import java.sql.Timestamp;

public class EstudianteCarreraDTO {
	private Timestamp fechaInscripcion;
	private Timestamp fechaGraduacion;
	private EstudianteDTO e;
	private CarreraDTO c;
	private int antiguedad;

	public EstudianteCarreraDTO(EstudianteDTO e, CarreraDTO c, Timestamp fechaInscripcion, Timestamp fechaGaduacion, int antiguedad) {
		this.fechaInscripcion = fechaInscripcion;
		this.fechaGraduacion = fechaGaduacion;
		this.antiguedad = antiguedad;
		this.e = e;
		this.c = c;
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
	
	public Timestamp getFechaGraduacion() {
		return fechaGraduacion;
	}

	public EstudianteDTO getE() {
		return e;
	}

	public CarreraDTO getC() {
		return c;
	}

	@Override
	public String toString() {
		return "EstudianteCarreraDTO [Estudiante=" + e + "Carrera=" + c +", fechaInscripcion=" + fechaInscripcion + ", fechaGraduacion=" + fechaGraduacion + ", antiguedad=" + antiguedad + "]";
	}
	
}
