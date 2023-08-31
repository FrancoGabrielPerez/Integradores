package populateDB;

import java.sql.Connection;
import java.sql.SQLException;

public class TablesCreator {
    private Connection conn;

    public TablesCreator(Connection conn) {
        this.conn = conn;
    }

    public void createTables() throws SQLException {
        String tableCliente = "CREATE TABLE IF NOT EXISTS Cliente(" +
            "idCliente INT NOT NULL, " +
            "nombre VARCHAR(500), " +
            "email VARCHAR(500), " +
            "CONSTRAINT Cliente_pk PRIMARY KEY (idCliente))";
        this.conn.prepareStatement(tableCliente).execute();
        this.conn.commit();

        String tableProducto = "CREATE TABLE IF NOT EXISTS Producto(" +
            "idProducto INT NOT NULL, " + 
            "nombre VARCHAR(45), " +
            "valor FLOAT, " +
            "CONSTRAINT Producto_pk PRIMARY KEY (idProducto));" ;
        this.conn.prepareStatement(tableProducto).execute();
        this.conn.commit();

        String tableFactura_Producto = "CREATE TABLE IF NOT EXISTS Factura_Producto(" +
            "idFactura INT NOT NULL, " + 
            "idProducto INT NOT NULL, " +
            "cantidad INT NOT NULL);" ;
        this.conn.prepareStatement(tableFactura_Producto).execute();
        this.conn.commit();
        
        String tableFactura = "CREATE TABLE IF NOT EXISTS Factura(" +
            "idFactura INT NOT NULL, " + 
            "idCliente INT, " +
            "CONSTRAINT Factura_pk PRIMARY KEY (idFactura), " +
            "CONSTRAINT FOREIGN KEY (idCLiente) REFERENCES Cliente (idCliente));" ;
        this.conn.prepareStatement(tableFactura).execute();
        this.conn.commit();     
    }
}
