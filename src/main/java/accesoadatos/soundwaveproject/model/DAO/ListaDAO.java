package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Comentario;
import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListaDAO extends DAO {



    private static EntityManager manager;
    private static EntityManagerFactory emf;


    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Lista lista) {

    }

    public boolean update(Lista lista) {

    }

    public boolean delete(Lista lista){

    }

    public Lista find(int id) {

    }



    public List<Lista> findAll() {

    }


    public static List<Lista> getListasByUsuario(String usuarioDni) {

    }




    private List<Usuario> getSuscripcionesByListaId(int listaId) {

    }

    public static int getNumeroSuscriptores(String dniUsuario, int idLista) {

    }



    public static int insertarComentarioEnLista(int idLista, Comentario nuevoComentario) {

    }

    public static boolean agregarCancionALista(int idLista, int idCancion) {

    }

    public static boolean suscribirse(String dni, int id){

    }


}
