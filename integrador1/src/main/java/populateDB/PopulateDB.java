package populateDB;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entidades.Cliente;
import entidades.Factura;
import entidades.FacturaProducto;
import entidades.Producto;

public class PopulateDB {
    
    public void populateDataBase() throws Exception {
        List<Iterable<CSVRecord>> records = this.getData();
        
        String driver = "com.mysql.cj.jdbc.Driver";
        
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
        
        String uri = "jdbc:mysql://localhost:3306/integrador1";//consultar si va harcodeado o se crea la db con CREATE DATABASE IF NOT EXISTS tp1_integrador;
        
        try {
            Connection conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
            TablesCreator tb = new TablesCreator(conn);
            tb.createTables();
            int i = 0;
            while (i < records.size()) {
                if (i == 0) {
                    for(CSVRecord row : records.get(i)) {
                        Cliente client = new Cliente(Integer.parseInt(row.get(0)), row.get(1), row.get(2));
                        insertClient(client, conn);
                    }
                }
                if (i == 1) {
                    for(CSVRecord row : records.get(i)) {
                        Factura bill = new Factura(Integer.parseInt(row.get(0)), Integer.parseInt(row.get(1)));
                        insertBill(bill, conn);
                    }
                }
                if (i == 2) {
                    for(CSVRecord row : records.get(i)) {
                        FacturaProducto bill = new FacturaProducto(Integer.parseInt(row.get(0)), Integer.parseInt(row.get(1)), Integer.parseInt(row.get(2)));
                        insertFacturaProducto(bill, conn);
                    }
                }
                if (i == 3) {
                    for(CSVRecord row : records.get(i)) {
                        Producto product = new Producto(Integer.parseInt(row.get(0)), row.get(1), Float.parseFloat(row.get(2)));
                        insertProducto(product, conn);
                    }
                }
                i++;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }       
    
    private List<Iterable<CSVRecord>> getData() throws Exception {
        Reader in;
        String[] archs = { "clientes", "facturas", "facturas-productos", "productos"};
        List<Iterable<CSVRecord>> records = new ArrayList<>(); 
    
        for (int i = 0; i < archs.length; i++) {
            Path currentWorkingDir = Paths.get("").toAbsolutePath();
            System.out.println(currentWorkingDir.normalize().toString());
            String DatasetPath = currentWorkingDir.normalize().toString() + "\\src\\main\\java\\datasets\\" + archs[i] +".csv";
            String Path = "integrador1\\src\\main\\java\\datasets\\" + archs[i] +".csv";
            System.out.println("PATH: " + Path);
            in = new FileReader(Path);
            String[] header = {};
            CSVParser csvParser = CSVFormat.EXCEL.builder().setHeader(header).build().parse(in);
            
            Iterable<CSVRecord> record = csvParser;
            records.add(record);
        }
        return records;
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
