package accesoadatos.soundwaveproject.model.Connection;

import accesoadatos.soundwaveproject.utils.Loggers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {

    private static EntityManagerFactory emf;
    private static Connection _newInstance;


    private Connection() {
        emf = Persistence.createEntityManagerFactory("sql");
        if (emf == null) {
            Loggers.LogsSevere("Error al establecer la conexión");
            emf = Persistence.createEntityManagerFactory("h2backup");
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
