
import helper.DBHelper;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main (String[] args) {
        ConnectionFactory conn = new ConnectionFactory();
        EntityManager em = conn.createConnection();
        DBHelper helper = new DBHelper(em);
        try {
            helper.populateDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.closeConnection(em);
    }
}
