package factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionFactory {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static EntityManager createConnection() {
		if (emf == null) {			
			emf = Persistence.createEntityManagerFactory("integrador2");
		}
		if (em == null) {
			em = emf.createEntityManager();
		}
		return em;
	}

	public static void closeConnection() {
		em.close();
		em = null;
		emf.close();
		emf = null;
	}    
}
