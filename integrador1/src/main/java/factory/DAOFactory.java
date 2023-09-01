package factory;

import dao.SystemDAO;
import entidades.Cliente;
import entidades.Factura;
import entidades.FacturaProducto;
import entidades.Producto;

public abstract class DAOFactory {

	// List of DAO types supported by the factory, string should be lowercase.
	public static final String MYSQL_JDBC = "mysql";
	public static final String DERBY_JDBC = "derby";
	public static final String JPA_HIBERNATE = "hibertnate";

	public abstract SystemDAO<Factura> getFacturaDAO();
	public abstract SystemDAO<FacturaProducto> getFacturaProductoDAO();
	public abstract SystemDAO<Producto> getProductoDAO();
	public abstract SystemDAO<Cliente> getClienteDAO(); 
	
	private static DAOFactory instanceMySQL = null;
	private static DAOFactory instanceDerby = null;
	private static DAOFactory instanceHibernate = null;
	
	public static DAOFactory getDAOFactory(String whichFactory) {
		switch (whichFactory.toLowerCase()) {
			case MYSQL_JDBC : {
				if (instanceMySQL == null) {
					instanceMySQL = new MySQLDAOFactory();
				}
				return instanceMySQL;
			}
			case DERBY_JDBC: return null;
			case JPA_HIBERNATE: return null;
			default: return null;
		}
	}
}






