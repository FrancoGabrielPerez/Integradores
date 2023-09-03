package factory;

import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.FacturaProductoDAO;
import dao.ProductoDAO;

public abstract class DAOFactory {

	// List of DAO types supported by the factory
	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	public static final int JPA_HIBERNATE = 3;

	public abstract FacturaDAO getFacturaDAO();
	public abstract FacturaProductoDAO getFacturaProductoDAO();
	public abstract ProductoDAO getProductoDAO();
	public abstract ClienteDAO getClienteDAO();
	public abstract void closeConnection();
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
			case MYSQL_JDBC : {
				return MySQLDAOFactory.getInstance();
			}
			case DERBY_JDBC: return null;
			case JPA_HIBERNATE: return null;
			default: return null;
		}
	}
}






