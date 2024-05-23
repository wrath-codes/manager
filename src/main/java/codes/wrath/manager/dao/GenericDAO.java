package codes.wrath.manager.dao;

import java.lang.reflect.ParameterizedType;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import codes.wrath.manager.util.HibernateUtil;

public class GenericDAO<Entity> {
    private Class<Entity> entityClass;

    // constructor creation
    @SuppressWarnings("unchecked")
    public GenericDAO() {

        this.entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public void save(Entity entity) {
        // first we need to capture an open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (RuntimeException error) {
            if (transaction != null) {
                transaction.rollback();
                throw error;
            }
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Entity> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria query = session.createCriteria(entityClass);
            List<Entity> result = query.list();
            return result;
        } catch (RuntimeException error) {
            throw error;
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public Entity search(Long code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria query = session.createCriteria(entityClass);
            query = query.add(Restrictions.idEq(code));
            Entity result = (Entity) query.uniqueResult();
            return result;
        } catch (RuntimeException error) {
            throw error;
        } finally {
            session.close();
        }
    }

    public void delete(Entity entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (RuntimeException error) {
            if (transaction != null) {
                transaction.rollback();
                throw error;
            }
        } finally {
            session.close();
        }
    }

    public void update(Entity entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (RuntimeException error) {
            if (transaction != null) {
                transaction.rollback();
                throw error;
            }
        } finally {
            session.close();
        }
    }

    public void merge(Entity entity) {
        // first we need to capture an open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (RuntimeException error) {
            if (transaction != null) {
                transaction.rollback();
                throw error;
            }
        } finally {
            session.close();
        }
    }
}
