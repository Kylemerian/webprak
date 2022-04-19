package ru.webprak.Dao.Impl;

import ru.webprak.Dao.OrdersDao;
import ru.webprak.Models.Orders;
import ru.webprak.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrdersDaoImpl implements OrdersDao {
    public void create(Orders order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(order);
        tx1.commit();
        session.close();
    }

    public void update(Orders order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(order);
        tx1.commit();
        session.close();

    }

    public void delete(Orders order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(order);
        tx1.commit();
        session.close();
    }

    @Override
    public Orders readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Orders order = session.get(Orders.class, id);
        session.close();
        return order;
    }

    @Override
    public List<Orders> readOrdersById(int customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Orders> query = session.createQuery("FROM Orders WHERE customer_id = :param", Orders.class).setParameter("param", customer);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
    @Override
    public List<Orders> readOrders() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Orders> query = session.createQuery("FROM Orders", Orders.class);
        List<Orders> obj = query.list();
        session.getTransaction().commit();
        session.close();
        return obj;
    }
}
