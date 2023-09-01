package dto;

public class InformeClienteMasFacturacion {
    private String nombre;
    private String email;
    private Float facturacion;

    public InformeClienteMasFacturacion(String nombre, String email, Float facturacion) {
        this.nombre = nombre;
        this.email = email;
        this.facturacion = facturacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Float getFacturacion() {
        return facturacion;
    }

    @Override
    public String toString() {
        return "informeCliente [nombre=" + nombre + ", email=" + email + ", facturacion=" + facturacion + "]";
    }    
}
