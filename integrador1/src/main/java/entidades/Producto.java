package entidades;

public class Producto {
    private int idProducto;
    private String nombre;
    private float valor;

    public Producto(int idPrducto, String nombre, float valor) {
        this.idProducto = idPrducto;
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", valor=" + valor + "]";
    }    
}
