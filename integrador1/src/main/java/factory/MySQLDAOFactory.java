package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


import dao.MySQLClienteDAO;
import dao.MySQLFacturaDAO;
import dao.MySQLFacturaProductoDAO;
import dao.MySQLProductoDAO;
import dao.ProductoDAO;
import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.FacturaProductoDAO;

public class MySQLDAOFactory extends DAOFactory {
    
    private static MySQLDAOFactory instance = null;

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String uri = "jdbc:mysql://localhost:3306/integrador1";
    public static Connection conn;

    private MySQLDAOFactory() {
    }

    public static synchronized MySQLDAOFactory getInstance() {
        if (instance == null) {
            instance = new MySQLDAOFactory();
        }
        return instance;
    }

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
    public FacturaDAO getFacturaDAO() {
        return new MySQLFacturaDAO(createConnection());
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() {
        return new MySQLFacturaProductoDAO(createConnection());
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return new MySQLProductoDAO(createConnection());
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new MySQLClienteDAO(createConnection());
    }     
   
}
