package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Lista;
import accesoadatos.soundwaveproject.model.Comentario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO extends DAO{

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Comentario c){
        return super.create(c);
    }
    public boolean update(Comentario c){
        return super.update(c);

    }
    public boolean delete(Comentario c) {
        return super.delete(c,c.getClass(),c.getId());

    }

    public Comentario find(int id){
        return (Comentario) super.find(id, Comentario.class);
    }

    public List<Comentario> findAll() throws SQLException {

    }


    public List<Comentario> findCommentsByListaId(int idLista){

    }


    public List<Comentario> findAllByUser(String dniUsuario){

    }

    public List<Comentario> findAllByList(int listaId){

    }


}
