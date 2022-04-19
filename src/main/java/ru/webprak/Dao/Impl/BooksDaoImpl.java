package ru.webprak.Dao.Impl;

import org.jetbrains.annotations.NotNull;
import ru.webprak.Dao.BooksDao;
import ru.webprak.Models.Books;
import ru.webprak.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BooksDaoImpl implements BooksDao {
    @Override
    public void create(@NotNull Books book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(book);
        tx1.commit();
        session.close();
    }
    @Override
    public void update(@NotNull Books book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(book);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(@NotNull Books book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(book);
        tx1.commit();
        session.close();
    }

    @Override
    public Books readByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Books object = session.get(Books.class, id);
        session.close();
        return object;
    }
    @Override
    public List<Books> readListByTitle(@NotNull String title) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE title = :param", Books.class).setParameter("param", title);
        List<Books> obj = query.list();
        session.close();
        return obj;
    }

    @Override
    public List<Books> readListByGenre(@NotNull String genre) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE genre = :param", Books.class).setParameter("param", genre);
        List<Books> obj = query.list();
        session.close();
        return obj;
    }
    @Override
    public List<Books> readListByAuthor(@NotNull String author) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE author = :param", Books.class).setParameter("param", author);
        List<Books> obj = query.list();
        session.close();
        return obj;
    }
    @Override
    public List<Books> readListByPubHouse(@NotNull String pub_house) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE pub_house = :param", Books.class).setParameter("param", pub_house);
        List<Books> obj = query.list();
        session.close();
        return obj;
    }
    @Override
    public int bookAmount(@NotNull Books book) {
        return book.getAmount();
    }
    @Override
    public int bookFreeAmount(@NotNull Books book) {
        return book.getFreeAmount();
    }
    @Override
    public List<Books> AllBooks() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Books> query = session.createQuery("FROM Books", Books.class);
        List<Books> obj = query.list();
        session.getTransaction().commit();
        session.close();
        return obj;
    }
}
