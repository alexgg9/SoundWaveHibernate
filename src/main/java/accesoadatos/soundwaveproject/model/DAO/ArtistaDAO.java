package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Artista;
import accesoadatos.soundwaveproject.model.Comentario;
import accesoadatos.soundwaveproject.model.Disco;
import accesoadatos.soundwaveproject.model.SQLConnection.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDAO extends DAO{


    private static EntityManager manager;
    private static EntityManagerFactory emf;


    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Artista artista){
        return super.create(artista);
    }

    public boolean update(Artista artista){
        return super.create(artista);
    }

    public boolean delete(Artista artista){
        return super.deleteU(artista, Artista.class, artista.getDni());
    }

    public Artista find(String dni){
        return (Artista) super.findU(dni, Comentario.class);
    }

    public Artista findByNombre(String nombre) {
        manager = Connection.getConnect().createEntityManager();
        TypedQuery<Artista> query = manager.createQuery("SELECT a FROM Artista a WHERE a.nombre = :nombre", Artista.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }

    public List<Disco> getDiscosByArtista(Artista artista) {
        EntityManager manager = Connection.getConnect().createEntityManager();
        TypedQuery<Disco> query = manager.createQuery("SELECT d FROM Disco d WHERE d.artista = :dniArtista", Disco.class);
        query.setParameter("dniArtista", artista.getDni());
        return query.getResultList();
    }





}
