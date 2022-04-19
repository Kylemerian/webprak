package ru.webprak.Dao.Impl;

import ru.webprak.Dao.CustomersDao;
import ru.webprak.Models.Customers;
import ru.webprak.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomersDaoImpl implements CustomersDao {
    public void create(Customers customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(customer);
        tx1.commit();
        session.close();
    }

    public void update(Customers customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(customer);
        tx1.commit();
        session.close();
    }

    public void delete(Customers customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(customer);
        tx1.commit();
        session.close();

    }

    @Override
    public Customers readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Customers object = session.get(Customers.class, id);
        session.close();
        return object;
    }

    @Override
    public List<Customers> readCustomers() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Customers> query = session.createQuery("FROM Customers ", Customers.class);
        List<Customers> obj = query.list();
        session.getTransaction().commit();
        session.close();
        return obj;
    }
}
