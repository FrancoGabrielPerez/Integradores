import jakarta.persistence.EntityManager;

public class Main {
    public static void main (String[] args) {
        ConnectionFactory conn = new ConnectionFactory();
        EntityManager em = conn.createConnection();
        conn.closeConnection(em);
    }
}
