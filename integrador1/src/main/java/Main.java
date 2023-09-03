import java.util.List;

import dao.ProductoDAO;
import dao.ClienteDAO;
import dto.InformeClienteMasFacturacion;
import dto.InformeProdMasRecaudacion;
import factory.DAOFactory;
import helper.DBHelper;

public class Main {
     public static void main(String[] args) throws Exception {

          DBHelper dbHelper = new DBHelper();
          dbHelper.dropTables();
          dbHelper.createTables();
          dbHelper.populateDB();
          dbHelper.closeConnection();
          
          DAOFactory systemFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);

          System.out.println();
          System.out.println("////////////////////////////////////////////");
          System.out.println();
          ProductoDAO producto = systemFactory.getProductoDAO();
          InformeProdMasRecaudacion ProductoConMasRecaudacion = producto.selectProductoConMasRecaudacion();
          System.out.println(ProductoConMasRecaudacion);
          ClienteDAO cliente = systemFactory.getClienteDAO();
          List<InformeClienteMasFacturacion> clientesConMasFacturacion = cliente.selectClientesConMasFacturacion();
          System.out.println();
          System.out.println("////////////////////////////////////////////");
          System.out.println();
          System.out.println("Lista de clientes ordenada por facturacion: ");
          for (InformeClienteMasFacturacion clienteMF : clientesConMasFacturacion) {
               System.out.println(clienteMF);
          }
          System.out.println();
          systemFactory.closeConnection();
     }
}
