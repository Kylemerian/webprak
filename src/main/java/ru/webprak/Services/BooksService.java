package ru.webprak.Services;

import ru.webprak.Dao.BooksDao;
import ru.webprak.Dao.Impl.BooksDaoImpl;
import ru.webprak.Models.Books;

import java.util.List;

public class BooksService {
    private final BooksDao booksDao = new BooksDaoImpl();
    public void createBook(Books book) {
        booksDao.create(book);
    }

    public void deleteBook(Books book) {
        booksDao.delete(book);
    }

    public void updateBook(Books book) {
        booksDao.update(book);
    }

    public Books readBookByID(int id) {
        return booksDao.readByID(id);
    }

    public List<Books> readBooksListByGenre(String genre) {
        return booksDao.readListByGenre(genre);
    }

    public List<Books> readBooksListByAuthor(String author) {
        return booksDao.readListByAuthor(author);
    }

    public List<Books> readBooksListByTitle(String title) {
        return booksDao.readListByTitle(title);
    }

    public List<Books> readAllBooks() {
        return booksDao.AllBooks();
    }

    public List<Books> readBooksListByPubHouse(String pub_house) {
        return booksDao.readListByPubHouse(pub_house);
    }

    public int readBookAmount(Books book) {
        return booksDao.bookAmount(book);
    }

    public int readBookFreeAmount(Books book) {
        return booksDao.bookFreeAmount(book);
    }
}
