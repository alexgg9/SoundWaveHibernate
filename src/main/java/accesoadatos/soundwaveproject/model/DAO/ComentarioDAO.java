package accesoadatos.soundwaveproject.model.DAO;


import accesoadatos.soundwaveproject.model.Comentario;
import accesoadatos.soundwaveproject.model.Connection.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class ComentarioDAO extends DAO<Comentario>{

    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public ComentarioDAO(Class<Comentario> entityClass) {
        super(entityClass);
    }


    //Los 4 primeros métodos son los que heredan de DAO<T>
    public boolean save(Comentario c){
        c.setFecha(LocalDate.now());
        return super.create(c);
    }
    public boolean update(Comentario c){
        return super.update(c);

    }
    public boolean delete(Comentario c) {
        return super.delete(c,c.getId());

    }

    public Comentario find(int id){
        return (Comentario) super.find(id, Comentario.class);
    }

    public List<Comentario> findAll() {
        manager = Connection.getConnect().createEntityManager();
        Query query = manager.createQuery("SELECT c FROM Comentario c");
        return query.getResultList();
    }

    public List<Comentario> findCommentsByListaId(int idLista) {
        manager = Connection.getConnect().createEntityManager();
        Query query = manager.createQuery("SELECT c FROM Comentario c WHERE c.lista.id = :idLista");
        query.setParameter("idLista", idLista);
        return query.getResultList();
    }

    public List<Comentario> findAllByUser(String dniUsuario) {
        manager = Connection.getConnect().createEntityManager();
        Query query = manager.createQuery("SELECT c FROM Comentario c WHERE c.usuario.dni = :dniUsuario");
        query.setParameter("dniUsuario", dniUsuario);
        return query.getResultList();
    }

    public List<Comentario> findAllByList(int listaId) {
        manager = Connection.getConnect().createEntityManager();
        Query query = manager.createQuery("SELECT c FROM Comentario c WHERE c.lista.id = :listaId");
        query.setParameter("listaId", listaId);
        return query.getResultList();
    }


}
