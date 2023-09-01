import java.util.List;

import dao.MySQLClienteDAO;
import dao.MySQLProductoDAO;
import dao.SystemDAO;
import dto.InformeClienteMasFacturacion;
import dto.InformeProdMasRecaudacion;
import entidades.Factura;
import factory.DAOFactory;

public class Main {
     public static void main(String[] args) throws Exception {

          DBHelper dbHelper = new DBHelper();
          dbHelper.createTables();
          dbHelper.populateDB();
          dbHelper.closeConnection();
          
          DAOFactory systemFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);

          System.out.println();
          MySQLClienteDAO cliente = (MySQLClienteDAO) systemFactory.getClienteDAO();
          List<InformeClienteMasFacturacion> clientesConMasFacturacion = cliente.selectClientesConMasFacturacion();
          System.out.println(clientesConMasFacturacion);
          MySQLProductoDAO producto = (MySQLProductoDAO) systemFactory.getProductoDAO();
          List<InformeProdMasRecaudacion> ProductoConMasRecaudacion = producto.selectProductoConMasRecaudacion();
          System.out.println(ProductoConMasRecaudacion);
          systemFactory.closeConnection();
     }
}
