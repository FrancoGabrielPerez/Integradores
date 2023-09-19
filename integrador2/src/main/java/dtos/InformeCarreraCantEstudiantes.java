package dtos;

public class InformeCarreraCantEstudiantes {
    private String nombre;
    private int cantEstudiantes;

    public InformeCarreraCantEstudiantes(String nombre, int cantEstudiantes) {
        this.nombre = nombre;
        this.cantEstudiantes = cantEstudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantEstudiantes() {
        return cantEstudiantes;
    }

    @Override
    public String toString() {
        return "InformeCarreraCantEstudiantes [nombre=" + nombre + ", cantEstudiantes=" + cantEstudiantes + "]";
    }    
}
