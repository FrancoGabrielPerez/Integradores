package dtos;

public class EstudianteDTO {
    private String nombre;
    private String apellido;
	private int edad;
    private String ciudad_residencia;
    private String genero;
	private Integer dni;
	private Integer libreta;

    public EstudianteDTO(String nombre, String apellido, int edad, String ciudad_residencia, String genero,
            Integer dni, Integer libreta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad_residencia = ciudad_residencia;
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

    public String getCiudad_residencia() {
        return ciudad_residencia;
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

    @Override
    public String toString() {
        return "EstudianteDTO [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
                + ", ciudad_residencia=" + ciudad_residencia + ", genero=" + genero + ", dni=" + dni + ", libreta="
                + libreta + "]";
    }    
}