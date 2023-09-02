package helper;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entidades.Cliente;
import entidades.Factura;
import entidades.FacturaProducto;
import entidades.Producto;

public class DBHelper {

    private Connection conn = null;
    
    public DBHelper() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/integrador1";
        
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
        
        try {           
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dropTables() throws SQLException {
        String dropProducto = "DROP TABLE IF EXISTS Producto";
        this.conn.prepareStatement(dropProducto).execute();
        this.conn.commit();
        
        String dropFactura_Producto = "DROP TABLE IF EXISTS Factura_Producto";
        this.conn.prepareStatement(dropFactura_Producto).execute();
        this.conn.commit();
        
        String dropFactura = "DROP TABLE IF EXISTS Factura";
        this.conn.prepareStatement(dropFactura).execute();
        this.conn.commit();

        String dropCliente = "DROP TABLE IF EXISTS Cliente";
        this.conn.prepareStatement(dropCliente).execute();
        this.conn.commit();
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

    public void populateDB() throws Exception {
        try {
            System.out.println("Populating DB...");
            for(CSVRecord row : getData("clientes.csv")) {
                Cliente client = new Cliente(Integer.parseInt(row.get(0)), row.get(1), row.get(2));
                insertClient(client, conn);
            }
            System.out.println("Clientes insertados");
            for(CSVRecord row : getData("facturas.csv")) {
                Factura bill = new Factura(Integer.parseInt(row.get(0)), Integer.parseInt(row.get(1)));
                insertBill(bill, conn);
            }
            System.out.println("Facturas insertadas");
            for(CSVRecord row : getData("facturas-productos.csv")) {
                FacturaProducto item = new FacturaProducto(Integer.parseInt(row.get(0)), Integer.parseInt(row.get(1)), Integer.parseInt(row.get(2)));
                insertFacturaProducto(item, conn);
            }
            System.out.println("Facturas-Productos insertados");
            for(CSVRecord row : getData("productos.csv")) {
                Producto product = new Producto(Integer.parseInt(row.get(0)), row.get(1), Float.parseFloat(row.get(2)));
                insertProducto(product, conn);
            }
            System.out.println("Productos insertados");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }       
    
    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "integrador1\\src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.builder().setHeader(header).build().parse(in);
        
        Iterable<CSVRecord> record = csvParser;
        return record;
    }

    private int insertClient(Cliente client, Connection conn) throws Exception {
        String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, client.getIdCliente());
            ps.setString(2, client.getNombre());
            ps.setString(3, client.getEmail());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertBill(Factura bill, Connection conn) throws Exception {
        String insert = "INSERT INTO Factura (idCliente, idFactura) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, bill.getIdCliente());
            ps.setInt(2, bill.getIdFactura());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertFacturaProducto(FacturaProducto billProduct, Connection conn) throws Exception {
        String insert = "INSERT INTO Factura_Producto (idProducto, idFactura, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, billProduct.getIdFactura());
            ps.setInt(2, billProduct.getIdProducto());
            ps.setInt(3, billProduct.getCantidad());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertProducto(Producto product, Connection conn) throws Exception {
        String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, product.getIdProducto());
            ps.setString(2, product.getNombre());
            ps.setFloat(3, product.getValor());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null){
            try {                    
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
   
}
