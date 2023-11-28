package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Artista;
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

    }

    public boolean update(Artista artista){

    }

    public boolean delete(Artista artista){

    }

    public Artista find(int id){

    }

    public Artista findByNombre(String nombre){

    }


    public List<Disco> getDiscosByArtista(Artista artista) throws SQLException {

    }




}
