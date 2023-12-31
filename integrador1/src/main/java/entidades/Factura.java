package entidades;

public class Factura {
    private int idFactura;
    private int idCliente;

    public Factura(int idFactura, int idCliente) {
        this.idCliente = idCliente;
        this.idFactura = idFactura;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    @Override
    public String toString() {
        return "Factura [idFactura=" + idFactura + ", idCliente=" + idCliente + "]";
    }    
    
}
