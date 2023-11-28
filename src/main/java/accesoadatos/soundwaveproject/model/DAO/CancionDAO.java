package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.Disco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancionDAO extends DAO{


    private static EntityManager manager;
    private static EntityManagerFactory emf;

    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Cancion cancion) {

    }
    public boolean update(Cancion cancion){

    }
    public boolean delete(Cancion cancion){

    }

    public Cancion find(int id){

    }

    public List<Cancion> getAllCanciones() {

    }

}

