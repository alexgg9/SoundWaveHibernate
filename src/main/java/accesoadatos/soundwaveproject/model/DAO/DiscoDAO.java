package accesoadatos.soundwaveproject.model.DAO;
import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.Connection.Connection;
import accesoadatos.soundwaveproject.model.Disco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.*;
import java.util.List;

public class DiscoDAO extends DAO{

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public DiscoDAO(Class entityClass) {
        super(entityClass);
    }


    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Disco disco){return super.create(disco);
    }

    public boolean update(Disco disco){
        return super.update(disco);
    }

    public boolean delete(Disco disco){
        return super.delete(disco,disco.getId());
    }

    public Disco find(int id){
        return (Disco) super.find(id, Disco.class);
    }

    public Disco getDiscoByNombre(String nombre){
        try {
            manager = Connection.getConnect().createEntityManager();
            manager.getTransaction().begin();

            // Crear una consulta para obtener el disco por nombre
            Query query = manager.createQuery("SELECT d FROM Disco d WHERE d.nombre = :nombre");
            query.setParameter("nombre", nombre);

            Disco disco = (Disco) query.getSingleResult();

            manager.getTransaction().commit();
            return disco;
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }


    public List<Cancion> getCancionesByDiscoId(int discoId){
        try {
            manager = Connection.getConnect().createEntityManager();
            manager.getTransaction().begin();

            // Crear una consulta para obtener todas las canciones de un disco por su ID
            Query query = manager.createQuery("SELECT c FROM Cancion c WHERE c.disco.id = :discoId");
            query.setParameter("discoId", discoId);

            List<Cancion> canciones = query.getResultList();

            manager.getTransaction().commit();
            return canciones;
        } finally {
            if (manager != null) {
                manager.close();
            }
        }

    }

    public List<Disco> getAll() throws SQLException {
        try {
            manager = Connection.getConnect().createEntityManager();
            manager.getTransaction().begin();

            // Crear una consulta para obtener todos los discos
            Query query = manager.createQuery("SELECT d FROM Disco d");
            List<Disco> discos = query.getResultList();

            manager.getTransaction().commit();
            return discos;
        } finally {
            if (manager != null) {
                manager.close();
            }
        }

    }

}

