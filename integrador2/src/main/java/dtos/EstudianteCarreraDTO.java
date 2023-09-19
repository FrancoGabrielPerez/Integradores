package dtos;

import java.sql.Timestamp;

public class EstudianteCarreraDTO {
    private Timestamp fechaInscripcion;
	private boolean graduado;	
    private int antiguedad;

    public EstudianteCarreraDTO(Timestamp fechaInscripcion, boolean graduado, int antiguedad) {
        this.fechaInscripcion = fechaInscripcion;
        this.graduado = graduado;
        this.antiguedad = antiguedad;
    }

    public Timestamp getFechaInscripcion() {
        return fechaInscripcion;
    }

    public boolean isGraduado() {
        return graduado;
    }

    public int getAntiguedad() {
        return antiguedad;
    }
    
    @Override
    public String toString() {
        return "EstudianteCarreraDTO [fechaInscripcion=" + fechaInscripcion + ", graduado=" + graduado + ", antiguedad="
                + antiguedad + "]";
    }

    
}
