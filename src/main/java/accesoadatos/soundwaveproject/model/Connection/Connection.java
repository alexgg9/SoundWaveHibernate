package accesoadatos.soundwaveproject.model.Connection;

import accesoadatos.soundwaveproject.utils.Loggers;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {

    private static EntityManagerFactory emf;
    private static Connection _newInstance;


    private Connection() {
        try {
            emf = Persistence.createEntityManagerFactory("sql");
        } catch (Exception e) {
            Loggers.LogsSevere("Error al establecer la conexión para 'sql': " + e.getMessage());
            try {
                emf = Persistence.createEntityManagerFactory("h2backup");
            } catch (Exception ex) {
                Loggers.LogsSevere("Error al establecer la conexión para 'h2backup': " + ex.getMessage());
            }
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
