package accesoadatos.soundwaveproject.model.DAO;
import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.Disco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.*;
import java.util.List;

public class DiscoDAO extends DAO{

    private static EntityManager manager;
    private static EntityManagerFactory emf;


    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Disco disco){

    }

    public boolean update(Disco disco){

    }

    public boolean delete(Disco disco){

    }

    public Disco find(int id){

    }

    public Disco getDiscoByNombre(String nombre){

    }


    public List<Cancion> getCancionesByDiscoId(int discoId){

    }

    public List<Disco> getAll() throws SQLException {

    }

}

