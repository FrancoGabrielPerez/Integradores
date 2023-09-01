package dto;

public class InformeProdMasRecaudacion {
    private String nombre;
    private int cantidad;
    private Float recaudacion;

    public InformeProdMasRecaudacion(String nombre, int cantidad, Float recaudacion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.recaudacion = recaudacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Float getRecaudacion() {
        return recaudacion;
    }

    @Override
    public String toString() {
        return "InformeProdMasRecaudacion [nombre=" + nombre + ", cantidad=" + cantidad + ", recaudacion=" + recaudacion
                + "]";
    }    
}
