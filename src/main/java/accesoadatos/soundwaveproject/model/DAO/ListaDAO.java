package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Cancion;
import accesoadatos.soundwaveproject.model.Comentario;
import accesoadatos.soundwaveproject.model.Connection.Connection;
import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ListaDAO extends DAO<Lista>{

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public ListaDAO(Class<Lista> entityClass) {

        super(entityClass);
    }


    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Lista l) {
        return super.create(l);
    }

    public boolean update(Lista l) {

        return super.create(l);
    }

    public boolean delete(Lista l){

        return super.delete(l,l.getId());
    }

    public Lista find(int id) {

        return (Lista) super.find(id, Lista.class);
    }



    public List<Lista> findAll() {
        manager = Connection.getConnect().createEntityManager();
        try {
            manager.getTransaction().begin();
            TypedQuery<Lista> query = manager.createQuery("SELECT l FROM Lista l", Lista.class);
            List<Lista> listas = query.getResultList();
            manager.getTransaction().commit();
            return listas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            manager.close();
        }
    }


    public List<Lista> getListasByUsuario(String usuarioDni) {
        manager = Connection.getConnect().createEntityManager();
        try {
            manager.getTransaction().begin();
            TypedQuery<Lista> query = manager.createQuery(
                    "SELECT l FROM Lista l WHERE l.creador.dni = :usuarioDni", Lista.class);
            query.setParameter("usuarioDni", usuarioDni);
            List<Lista> listas = query.getResultList();
            manager.getTransaction().commit();
            return listas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            manager.close();
        }
    }




    private List<Usuario> getSuscripcionesByListaId(int listaId) {
        manager = Connection.getConnect().createEntityManager();
        try {
            manager.getTransaction().begin();
            Lista lista = manager.find(Lista.class, listaId);
            List<Usuario> suscriptores = lista.getSuscriptores();
            manager.getTransaction().commit();
            return suscriptores;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            manager.close();
        }
    }

    public static int getNumeroSuscriptores(String dniUsuario, int idLista) {
        manager = Connection.getConnect().createEntityManager();
        try {
            manager.getTransaction().begin();
            Lista lista = manager.find(Lista.class, idLista);
            int numeroSuscriptores = lista.getSuscriptores().size();
            manager.getTransaction().commit();
            return numeroSuscriptores;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Puedes manejar el error de otra manera si es necesario
        } finally {
            manager.close();
        }
    }



    public static int insertarComentarioEnLista(int idLista, Comentario nuevoComentario) {
        manager = Connection.getConnect().createEntityManager();
        try {
            manager.getTransaction().begin();
            Lista lista = manager.find(Lista.class, idLista);
            nuevoComentario.setLista(lista);
            lista.getComentarios().add(nuevoComentario);
            manager.persist(nuevoComentario);
            manager.getTransaction().commit();
            return nuevoComentario.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Puedes manejar el error de otra manera si es necesario
        } finally {
            manager.close();
        }
    }

    public static boolean agregarCancionALista(int idLista, int idCancion) {
        manager = Connection.getConnect().createEntityManager();
        try {
            manager.getTransaction().begin();
            Lista lista = manager.find(Lista.class, idLista);
            Cancion cancion = manager.find(Cancion.class, idCancion);
            if (lista != null && cancion != null) {
                lista.getCanciones().add(cancion);
                manager.getTransaction().commit();
                return true;
            } else {
                manager.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            manager.close();
        }
    }

    public static boolean suscribirse(String dni, int id) {
        manager = Connection.getConnect().createEntityManager();
        try {
            manager.getTransaction().begin();
            Usuario usuario = manager.find(Usuario.class, dni);
            Lista lista = manager.find(Lista.class, id);
            lista.getSuscriptores().add(usuario);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            manager.close();
        }
    }


}
