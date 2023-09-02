import java.util.List;

import dao.MySQLClienteDAO;
import dao.MySQLProductoDAO;
import dao.SystemDAO;
import dto.InformeClienteMasFacturacion;
import dto.InformeProdMasRecaudacion;
import entidades.Factura;
import factory.DAOFactory;
import helper.DBHelper;

public class Main {
     public static void main(String[] args) throws Exception {

          // DBHelper dbHelper = new DBHelper();
          // dbHelper.dropTables();
          // dbHelper.createTables();
          // dbHelper.populateDB();
          // dbHelper.closeConnection();
          
          DAOFactory systemFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);

          System.out.println();
          MySQLClienteDAO cliente = (MySQLClienteDAO) systemFactory.getClienteDAO();
          List<InformeClienteMasFacturacion> clientesConMasFacturacion = cliente.selectClientesConMasFacturacion();
          System.out.println("Lista de clientes con mas facturacion: ");
          for (InformeClienteMasFacturacion clienteMF : clientesConMasFacturacion) {
               System.out.println(clienteMF);
          }
          System.out.println();
          MySQLProductoDAO producto = (MySQLProductoDAO) systemFactory.getProductoDAO();
          InformeProdMasRecaudacion ProductoConMasRecaudacion = producto.selectProductoConMasRecaudacion();
          System.out.println(ProductoConMasRecaudacion);
          systemFactory.closeConnection();
     }
}
