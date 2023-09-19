package dtos;

public class CarreraDTO {
    private Integer id;
    private String nombre;

    public CarreraDTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "CarreraDTO [id=" + id + ", nombre=" + nombre + "]";
    }    
}
