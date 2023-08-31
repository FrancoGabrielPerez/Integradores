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
    public static final String DBURL = "jdbc:mysql://localhost:3306/tp1_integrador";
    public static Connection conn;
    
    public static Connection createConnection() {   
        String driver = DRIVER;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String uri = DBURL;

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    return conn;                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
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
