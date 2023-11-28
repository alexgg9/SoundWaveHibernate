package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO extends DAO {




    private static EntityManager manager;
    private static EntityManagerFactory emf;

    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Usuario usuario){

    }

    public boolean update(Usuario usuario) {

    }

    public boolean delete(Usuario usuario){

    }

    public Usuario find(String dni){

    }

    private boolean usuarioExists(String dni){

    }

    public static List<Usuario> getAll(){

    }


    public Usuario getByCorreo(String correo){

    }


    public Usuario getByNombreUsuario(String nombreUsuario){

    }





}
