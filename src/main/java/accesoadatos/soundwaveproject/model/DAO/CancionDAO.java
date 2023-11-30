package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.Connection.Connection;
import accesoadatos.soundwaveproject.model.Disco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancionDAO extends DAO{


    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public CancionDAO(Class entityClass) {
        super(entityClass);
    }

    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Cancion cancion) {
        return super.create(cancion);
    }
    public boolean update(Cancion cancion){
        return super.update(cancion);
    }
    public boolean delete(Cancion cancion){
        return super.delete(cancion,cancion.getId());
    }

    public Cancion find(int id){
        return (Cancion) super.find(id, Cancion.class);
    }

    public List<Cancion> getAllCanciones() {
        manager = Connection.getConnect().createEntityManager();
        Query query = manager.createQuery("SELECT c FROM Cancion c");
        return query.getResultList();

    }

}

