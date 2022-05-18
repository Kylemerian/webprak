package ru.webprak.Dao.Impl;

import ru.webprak.Dao.InstancesDao;
import ru.webprak.Models.Instances;
import ru.webprak.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class InstancesDaoImpl implements InstancesDao {
    public void create(Instances instance) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(instance);
        tx1.commit();
        session.close();
    }

    public void update(Instances instance) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(instance);
        tx1.commit();
        session.close();

    }

    public void delete(Instances instance) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(instance);
        tx1.commit();
        session.close();
    }
    @Override
    public Instances readByBookIdByInstanceId(int book_id, int instance_id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Instances> query = session.createQuery("FROM Instances WHERE book_id = :param AND instance_id = :param2", Instances.class).setParameter("param", book_id).setParameter("param2", instance_id);
        return query.getSingleResult();
    }

    @Override
    public Instances readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Instances instance = session.get(Instances.class, id);
        session.close();
        return instance;
    }

    @Override
    public List<Instances> readInstancesById(int book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Instances> query = session.createQuery("FROM Instances WHERE book_id = :param", Instances.class).setParameter("param", book);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
    @Override
    public List<Instances> readInstances() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Instances> query = session.createQuery("FROM Instances", Instances.class);
        List<Instances> obj = query.list();
        session.close();
        return obj;
    }
}
