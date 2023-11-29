package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.SQLConnection.Connection;
import accesoadatos.soundwaveproject.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAO<Usuario> {
    private static EntityManager manager;
    private static EntityManagerFactory emf;

    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Usuario usuario) {
        return super.create(usuario);
    }

    public boolean update(Usuario usuario) {
        return super.update(usuario);
    }

    public boolean delete(Usuario usuario) {
        return super.delete(usuario, Usuario.class, usuario.getDni().hashCode());
    }

    public Usuario find(String dni) {
        return (Usuario) super.find(dni.hashCode(), Usuario.class);

    }

    private boolean usuarioExists(String dni) {
        manager = Connection.getConnect().createEntityManager();
        boolean result = false;
        try {
            manager.getTransaction().begin();
            Query q = manager.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.dni = :dni", Long.class);
            q.setParameter("dni", dni);
            Long count = (Long) q.getSingleResult();
            result = (count != null && count > 0);
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return result;
    }


    public static List<Usuario> getAll() {
        manager = Connection.getConnect().createEntityManager();
        List<Usuario> usuarios = new ArrayList<>();
        try {
            manager.getTransaction().begin();
            usuarios = manager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return usuarios;
    }

    public Usuario getByCorreo(String correo) {
        manager = Connection.getConnect().createEntityManager();
        Usuario usuario = null;
        try {
            manager.getTransaction().begin();
            Query q = manager.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class);
            q.setParameter("correo", correo);
            usuario = (Usuario) q.getSingleResult();
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return usuario;
    }


    public Usuario getByNombreUsuario(String nombreUsuario) {
        List<Usuario> misUsers = new ArrayList<Usuario>();
        manager = Connection.getConnect().createEntityManager();
        Query q = manager.createNativeQuery("SELECT * FROM usuario WHERE nombre = ?", Usuario.class);
        q.setParameter(1, nombreUsuario);
        misUsers = q.getResultList();
        Usuario aux = misUsers.get(0);
        manager.close();
        return aux;

    }


}
