package accesoadatos.soundwaveproject.model.DAO;

import accesoadatos.soundwaveproject.model.Connection.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DAO<T> {
    private static EntityManager manager;
    private final Class<T> entityClass;

    public DAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    public boolean create(T o) {
        boolean added = false;
        manager = Connection.getConnect().createEntityManager();
        if (!manager.contains(o)) {
            try {
                manager.getTransaction().begin();
                manager.persist(o);
                manager.getTransaction().commit();
                manager.close();
                added = true;
            } catch (Exception e) {
                e.printStackTrace();
                added = false;
            }
        }
        return added;
    }

    public boolean update(T o) {
        boolean updated = false;
        EntityManager manager = Connection.getConnect().createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            if (!manager.contains(o)) {
                o = manager.merge(o);
            }
            transaction.begin();
            manager.merge(o);
            transaction.commit();
            updated = true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (manager != null && manager.isOpen()) {
                manager.close();
            }
        }
        return updated;
    }

    public boolean delete(T o, int id) {
        boolean removed = false;
        manager = Connection.getConnect().createEntityManager();
        o = manager.find(entityClass, id);
        if (manager.contains(o)) {
            try {
                manager.getTransaction().begin();
                manager.remove(o);
                manager.getTransaction().commit();
                manager.close();
                removed = true;
            } catch (Exception e) {
                e.printStackTrace();
                removed = false;
            }
        }
        return removed;
    }

    public boolean deleteU(T o, String dni) {
        boolean removed = false;
        manager = Connection.getConnect().createEntityManager();
        o = manager.find(entityClass, dni);

        if (manager.contains(o)) {
            try {
                manager.getTransaction().begin();
                manager.remove(o);
                manager.getTransaction().commit();
                manager.close();
                removed = true;
            } catch (Exception e) {
                e.printStackTrace();
                removed = false;
            }
        }
        return removed;
    }

    public T find(int id, Class<T> entityClass) {
        manager = Connection.getConnect().createEntityManager();
        try {
            manager.getTransaction().begin();
            T o = manager.find(entityClass, id);
            manager.getTransaction().commit();
            manager.close();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public T findU(String dni, Class<T> entityClass) {
        manager = Connection.getConnect().createEntityManager();
        try {
            manager.getTransaction().begin();
            T o = manager.find(entityClass, dni);
            manager.getTransaction().commit();
            manager.close();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
