package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Artista;
import accesoadatos.soundwaveproject.model.Comentario;
import accesoadatos.soundwaveproject.model.Disco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
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

    public Artista findByNombre(String nombre){

    }


    public List<Disco> getDiscosByArtista(Artista artista) throws SQLException {

    }




}
