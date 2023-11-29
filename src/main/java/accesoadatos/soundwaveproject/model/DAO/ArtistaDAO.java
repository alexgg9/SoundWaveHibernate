package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Artista;
import accesoadatos.soundwaveproject.model.Comentario;
import accesoadatos.soundwaveproject.model.Disco;
import accesoadatos.soundwaveproject.model.Connection.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ArtistaDAO extends DAO<Artista>{


    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public ArtistaDAO(Class<Artista> entityClass) {
        super(entityClass);
    }


    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Artista artista){
        return super.create(artista);
    }

    public boolean update(Artista artista){
        return super.create(artista);
    }

    public boolean delete(Artista artista){
        return super.deleteU(artista, artista.getDni());
    }

    public Artista find(String dni){
        return (Artista) super.findU(dni, Artista.class);
    }

    public Artista findByNombre(String nombre) {
        EntityManager manager = Connection.getConnect().createEntityManager();
        TypedQuery<Artista> query = manager.createQuery("SELECT a FROM Artista a WHERE a.nombre = :nombre", Artista.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }

    public List<Disco> getDiscosByArtista(Artista artista) {
        EntityManager manager = Connection.getConnect().createEntityManager();
        TypedQuery<Disco> query = manager.createQuery("SELECT d FROM Disco d WHERE d.artista = :artista", Disco.class);
        query.setParameter("artista", artista);
        return query.getResultList();
    }

}
