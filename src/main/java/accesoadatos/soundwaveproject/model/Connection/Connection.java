package accesoadatos.soundwaveproject.model.Connection;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {

    private static EntityManagerFactory emf;
    private static Connection _newInstance;

    private Connection() {
        emf = Persistence.createEntityManagerFactory("sql");
        if (emf == null) {
            System.out.println("No se puede establecer la conexión");
        }
    }

    public static EntityManagerFactory getConnect() {
        if (_newInstance == null) {
            _newInstance = new Connection();
        }
        return emf;
    }

    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }

}
