package dtos;

public class ReporteCarrerasDTO {
    private int anio;
    private int cantInscriptos;
    private int cantEgresados;

    public ReporteCarrerasDTO(int anio, int cantInscriptos, int cantEgresados) {
        this.anio = anio;
        this.cantInscriptos = cantInscriptos;
        this.cantEgresados = cantEgresados;
    }

    public int getAnio() {
        return this.anio;
    }

    public int getCantInscriptos() {
        return this.cantInscriptos;
    }

    public int getCantEgresados() {
        return this.cantEgresados;
    }

    @Override
	public String toString() {
		return "ReporteCarrerasDTO [anio=" + anio + ", cantInscriptos=" + cantInscriptos + ", cantEgresados=" + cantEgresados + "]";
	}    
}