package dtos;

public class EstudianteDTO {
	private String nombre;
	private String apellido;
	private int edad;
	private String ciudadResidencia;
	private String genero;
	private Integer dni;
	private Integer libreta;

	public EstudianteDTO(String nombre, String apellido, int edad, String ciudadResidencia, String genero,
			Integer dni, Integer libreta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.ciudadResidencia = ciudadResidencia;
		this.genero = genero;
		this.dni = dni;
		this.libreta = libreta;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}
	
	public int getEdad() {
		return edad;
	}

	public String getCiudadResidencia() {
		return ciudadResidencia;
	}

	public String getGenero() {
		return genero;
	}

	public Integer getDni() {
		return dni;
	}

	public Integer getLibreta() {
		return libreta;
	}

	public String getNombreCompleto(){
		return this.apellido.toUpperCase() + ", " + this.nombre;
	}

	@Override
	public String toString() {
		return "Nombre: " + getNombreCompleto() + ", edad: " + edad	+ ", ciudad de residencia: " + ciudadResidencia + ", genero: " +
		genero + ", dni: " + dni + ", libreta: " + libreta;
	}    
}
