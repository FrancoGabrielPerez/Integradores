package dto;

public class InformeClienteMasFacturacion {
    private String nombre;
    private String email;
    private Float facturacion;    

    public InformeClienteMasFacturacion() {
        super();
    }

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
        return "Cliente: nombre = " + nombre + ", Email = " + email + ", Facturacion = $ " + facturacion;
    }    
}
