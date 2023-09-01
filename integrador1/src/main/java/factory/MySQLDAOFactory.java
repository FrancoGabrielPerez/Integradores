package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


import dao.MySQLClienteDAO;
import dao.MySQLFacturaDAO;
import dao.MySQLFacturaProductoDAO;
import dao.MySQLProductoDAO;
import dao.SystemDAO;
import entidades.Cliente;
import entidades.Factura;
import entidades.FacturaProducto;
import entidades.Producto;

public class MySQLDAOFactory extends DAOFactory {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String uri = "jdbc:mysql://localhost:3306/integrador1";
    public static Connection conn;

    public static Connection createConnection() { 
        if (conn != null) {
            return conn;
        } 
        String driver = DRIVER;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SystemDAO<Factura> getFacturaDAO() {
        return new MySQLFacturaDAO(createConnection());
    }

    @Override
    public SystemDAO<FacturaProducto> getFacturaProductoDAO() {
        return new MySQLFacturaProductoDAO(createConnection());
    }

    @Override
    public SystemDAO<Producto> getProductoDAO() {
        return new MySQLProductoDAO(createConnection());
    }

    @Override
    public SystemDAO<Cliente> getClienteDAO() {
        return new MySQLClienteDAO(createConnection());
    }     
   
}
