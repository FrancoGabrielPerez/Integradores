import java.util.List;

import dao.MySQLClienteDAO;
import dao.MySQLFacturaDAO;
import dao.SystemDAO;
import entidades.Cliente;
import entidades.Factura;
import factory.DAOFactory;

public class Main {
     public static void main(String[] args) throws Exception {

          DBHelper dbHelper = new DBHelper();
          
          DAOFactory systemFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);

          /* SystemDAO<Factura> factura = systemFactory.getFacturaDAO();

          List<Factura> nomina = factura.selectList();
          System.out.println(nomina.toString()); */

          System.out.println();
          MySQLClienteDAO cliente = (MySQLClienteDAO) systemFactory.getClienteDAO();
          List<Cliente> clientesConMasFacturacion = cliente.selectClientesConMasFacturacion();
          System.out.println(clientesConMasFacturacion);

        

          String productoConMasRecaudacion = "SELECT p.idProducto, p.valor, SUM(fp.cantidad * p.valor) AS total_recaudado " +
                    "FROM producto p " +
                    "JOIN factura_producto fp ON p.idProducto = fp.idProducto " +
                    "GROUP BY p.idProducto, p.valor" +
                    "ORDER BY total_recaudado DESC" +
                    "LIMIT 1;";

     }
}
