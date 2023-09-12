import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionFactory {
    
    private EntityManagerFactory emf;

    public EntityManager createConnection() {
        this.emf = Persistence.createEntityManagerFactory("integrador2");
        EntityManager em = emf.createEntityManager();
        return em;
    }

    public void closeConnection(EntityManager em) {
        em.close();
        this.emf.close();
    }    
}
