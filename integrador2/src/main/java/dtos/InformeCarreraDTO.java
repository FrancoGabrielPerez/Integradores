package dtos;

public class InformeCarreraDTO {
	private String carrera;
	private Integer año;
	private Long inscriptos;
	private Long graduados;
	
	public InformeCarreraDTO(String carrera, Integer año, Long inscriptos, Long graduados) {
		this.carrera = carrera;
		this.año = año;
		this.inscriptos = inscriptos;
		this.graduados = graduados;
	}

	public String getNombreCarrera() {
		return carrera;
	}

	public long getCantInscriptos() {
		return inscriptos;
	}

	public long getCantGraduados() {
		return graduados;
	}

	public int getAño() {
		return año;
	}

	@Override
	public String toString() {
		return "InformeCarreraCantEstudiantesDTO [carrera=" + carrera + ", año=" + año + ", inscriptos=" + inscriptos
				+ ", graduados=" + graduados + "]";
	}
}
