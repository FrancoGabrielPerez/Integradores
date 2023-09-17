import org.hibernate.dialect.H2Dialect;

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        conn.closeConnection(em);
    }
}
