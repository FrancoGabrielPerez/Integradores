package dtos;

public class InformeCarreraCantEstudiantesDTO {
    private String nombre;
    private Long cantEstudiantes;
    
    public InformeCarreraCantEstudiantesDTO(String nombre, Long cantEstudiantes) {
        this.nombre = nombre;
        this.cantEstudiantes = cantEstudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getCantEstudiantes() {
        return cantEstudiantes;
    }

    @Override
    public String toString() {
        return "InformeCarreraCantEstudiantes [nombre=" + nombre + ", cantEstudiantes=" + cantEstudiantes + "]";
    }    
}
