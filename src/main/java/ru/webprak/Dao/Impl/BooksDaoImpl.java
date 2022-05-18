package ru.webprak.Dao.Impl;

import org.jetbrains.annotations.NotNull;
import ru.webprak.Dao.BooksDao;
import ru.webprak.Dao.InstancesDao;
import ru.webprak.Models.Books;
import ru.webprak.Models.Instances;
import ru.webprak.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class BooksDaoImpl implements BooksDao {
    @Override
    public void create(Books book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        InstancesDao iDao = new InstancesDaoImpl();
        for(int i = 0; i < book.getAmount(); i++)
            iDao.create(new Instances(book.getBook_id(), i + 1, true));
        session.close();
    }
    @Override
    public void update(Books book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(book);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Books book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(book);
        session.getTransaction().commit();
        InstancesDao iDao = new InstancesDaoImpl();
        for(int i = 0; i < book.getAmount(); i++)
            iDao.delete(new Instances(book.getBook_id(), i + 1, true));
        session.close();
    }

    @Override
    public Books readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE book_id = :param", Books.class).setParameter("param", id);
        Books object;
        try {
            object = query.getSingleResult();
        } catch (NoResultException e) {
            object = null;
        }
        session.close();
        return object;
    }
    @Override
    public List<Books> readListByTitle(String title) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE title = :param", Books.class).setParameter("param", title);
        List<Books> obj = query.list();
        session.close();
        return obj;
    }

    @Override
    public List<Books> readListByGenre(String genre) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE genre = :param", Books.class).setParameter("param", genre);
        List<Books> obj = query.list();
        session.close();
        return obj;
    }
    @Override
    public List<Books> readListByAuthor(String author) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE author like :param", Books.class).setParameter("param", author);
        List<Books> obj = query.list();
        session.close();
        return obj;
    }
    @Override
    public List<Books> readListByPubHouse(String pub_house) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE pub_house = :param", Books.class).setParameter("param", pub_house);
        List<Books> obj = query.list();
        session.close();
        return obj;
    }
    @Override
    public int bookAmount(Books book) {
        return book.getAmount();
    }
    @Override
    public int bookFreeAmount(Books book) {
        return book.getFreeAmount();
    }
    @Override
    public List<Books> AllBooks() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books", Books.class);
        List<Books> obj = query.list();
        session.close();
        return obj;
    }
}
