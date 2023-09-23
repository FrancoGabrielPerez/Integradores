package dtos;

public class InformeCarreraDTO {
	private String carrera;
	private int año;
	private long inscriptos;
	private long graduados;
	
	public InformeCarreraDTO(String carrera, int año, Long inscriptos, Long graduados) {
		this.carrera = carrera;
		this.inscriptos = inscriptos;
		this.graduados = graduados;
	}

	public String getNombreCarrera() {
		return carrera;
	}

	public Long getCantInscriptos() {
		return inscriptos;
	}

	public Long getCantGraduados() {
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
