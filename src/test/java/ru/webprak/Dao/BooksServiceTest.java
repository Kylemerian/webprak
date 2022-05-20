package ru.webprak.Dao;

import ru.webprak.Models.Books;
import org.junit.*;
import ru.webprak.Services.BooksService;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BooksServiceTest {

    @Test
    public void testCreateBook() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(40, "Война и мир", "Л.Н. Толстой", "Роман", "Эксмо", 2021, 30, "12323");
        bookService.createBook(new_book);

        Books check_book = bookService.readBookByID(new_book.getBook_id());
        assertEquals(new_book, check_book);
        bookService.deleteBook(new_book);
    }

    @Test
    public void testDeleteBook() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(40, "Война и мир", "Л.Н. Толстой", "Роман", "Эксмо", 2021, 30, "12323");
        bookService.createBook(new_book);
        Books check_book = bookService.readBookByID(new_book.getBook_id());
        assertEquals(new_book, check_book);
        int s = new_book.getBook_id();
        bookService.deleteBook(new_book);
        check_book = bookService.readBookByID(s);
        Assert.assertNull(check_book);
    }

    @Test
    public void testUpdateBook() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(40, "Война и мир", "Л.Н. Толстой", "Роман", "Эксмо", 2021, 30, "12323");
        bookService.createBook(new_book);
        Books check_book = bookService.readBookByID(new_book.getBook_id());
        Assert.assertEquals(new_book, check_book);

        new_book.setGenre("Повесть");
        bookService.updateBook(new_book);
        check_book = bookService.readBookByID(new_book.getBook_id());
        Assert.assertEquals(new_book, check_book);
        bookService.deleteBook(new_book);
    }

    @Test
    public void testReadBookByID() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(40,"Война и мир", "Л.Н. Толстой", "Роман", "Эксмо", 2021, 30, "12323");
        bookService.createBook(new_book);
        Books check_book = bookService.readBookByID(new_book.getBook_id());
        Assert.assertEquals(new_book.getBook_id(), check_book.getBook_id());
        bookService.deleteBook(new_book);
    }

    @Test
    public void testReadBooksListByGenre() {
        BooksService bookService = new BooksService();
        Set<Books> expected_list = Set.of(
                new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323"),
                new Books(40,  "Война и мир 2", "А.С. Пушкин", "Повесть", "Эксмо 1", 2012, 30, "12323"),
                new Books(40,  "Война и мир 3", "А.С. Пушкин", "Сказка", "Эксмо 0", 2013, 30, "12323"),
                new Books(40,  "Война и мир 4", "Л.Н. Толстой", "Рассказ", "Эксмо 0", 2014, 30, "12323"),
                new Books(40,  "Война и мир 5", "Л.Н. Толстой", "Роман", "Эксмо 4", 2015, 30, "12323")
        );
        for(Books x : expected_list){
            bookService.createBook(x);
        }
        List<Books> list_of_books = bookService.readBooksListByGenre("Роман");
        Assert.assertEquals(list_of_books.size(), 2);
        Assert.assertTrue(expected_list.contains(list_of_books.get(0)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(1)));
        for(Books x : expected_list){
            bookService.deleteBook(x);
        }
    }

    @Test
    public void testReadBooksListByPubHouse() {
        BooksService bookService = new BooksService();
        Set<Books> expected_list = Set.of(
                new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323"),
                new Books(40,  "Война и мир 2", "А.С. Пушкин", "Повесть", "Эксмо 1", 2012, 30, "12323"),
                new Books(40,  "Война и мир 3", "А.С. Пушкин", "Сказка", "Эксмо 0", 2013, 30, "12323"),
                new Books(40,  "Война и мир 4", "Л.Н. Толстой", "Рассказ", "Эксмо 0", 2014, 30, "12323"),
                new Books(40,  "Война и мир 5", "Л.Н. Толстой", "Роман", "Эксмо 4", 2015, 30, "12323")
        );
        for(Books x : expected_list){
            bookService.createBook(x);
        }
        List<Books> list_of_books = bookService.readBooksListByPubHouse("Эксмо 0");
        Assert.assertEquals(list_of_books.size(), 3);
        Assert.assertTrue(expected_list.contains(list_of_books.get(0)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(1)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(2)));
        for(Books x : expected_list){
            bookService.deleteBook(x);
        }
    }

    @Test
    public void testReadBooksListByAuthor() {
        BooksService bookService = new BooksService();
        Set<Books> expected_list = Set.of(
                new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323"),
                new Books(40,  "Война и мир 2", "А.С. Пушкин", "Повесть", "Эксмо 1", 2012, 30, "12323"),
                new Books(40,  "Война и мир 3", "А.С. Пушкин", "Сказка", "Эксмо 0", 2013, 30, "12323"),
                new Books(40,  "Война и мир 4", "Л.Н. Толстой", "Рассказ", "Эксмо 0", 2014, 30, "12323"),
                new Books(40, "Война и мир 5", "Л.Н. Толстой", "Роман", "Эксмо 4", 2015, 30, "12323")
        );
        for(Books x : expected_list){
            bookService.createBook(x);
        }
        List<Books> list_of_books = bookService.readBooksListByAuthor("Л.Н. Толстой");
        Assert.assertEquals(list_of_books.size(), 3);
        Assert.assertTrue(expected_list.contains(list_of_books.get(0)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(1)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(2)));
        for(Books x : expected_list){
            bookService.deleteBook(x);
        }
    }

    @Test
    public void testReadBooksListByTitle() {
        BooksService bookService = new BooksService();
        Set<Books> expected_list = Set.of(
                new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323"),
                new Books(40,  "Война и мир 2", "А.С. Пушкин", "Повесть", "Эксмо 1", 2012, 30, "12323"),
                new Books(40,  "Война и мир 3", "А.С. Пушкин", "Сказка", "Эксмо 0", 2013, 30, "12323"),
                new Books(40,  "Война и мир 4", "Л.Н. Толстой", "Рассказ", "Эксмо 0", 2014, 30, "12323"),
                new Books(40, "Война и мир 5", "Л.Н. Толстой", "Роман", "Эксмо 4", 2015, 30, "12323")
        );
        for (Books x: expected_list)
            bookService.createBook(x);
        List<Books> list_of_books = bookService.readBooksListByTitle("Война и мир 3");
        Assert.assertEquals(1, list_of_books.size());
        Assert.assertTrue(expected_list.contains(list_of_books.get(0)));
        for(Books x: expected_list)
            bookService.deleteBook(x);
    }

    @Test
    public void testReadBookAmount() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(40,"Война и мир", "Л.Н. Толстой", "Роман", "Эксмо", 2021, 30, "12323");
        bookService.createBook(new_book);
        Assert.assertEquals(bookService.readBookAmount(new_book), new_book.getAmount());
        bookService.deleteBook(new_book);
    }

    @Test
    public void testReadBookFreeAmount() {
        BooksService bookService = new BooksService();
        Books new_book = new Books(50, "Война и мир", "Л.Н. Толстой", "Роман", "Эксмо", 2021, 30, "12323");
        bookService.createBook(new_book);
        Assert.assertEquals(bookService.readBookFreeAmount(new_book), new_book.getFreeAmount());
        bookService.deleteBook(new_book);
    }

    @Test
    public void testReadAllBooks() {
        BooksService bookService = new BooksService();
        Set<Books> expected_list = Set.of(
                new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323"),
                new Books(40,  "Война и мир 2", "А.С. Пушкин", "Повесть", "Эксмо 1", 2012, 30, "12323"),
                new Books(40,  "Война и мир 3", "А.С. Пушкин", "Сказка", "Эксмо 0", 2013, 30, "12323"),
                new Books(40,  "Война и мир 4", "Л.Н. Толстой", "Рассказ", "Эксмо 0", 2014, 30, "12323"),
                new Books(40, "Война и мир 5", "Л.Н. Толстой", "Роман", "Эксмо 4", 2015, 30, "12323")
        );
        for(Books x : expected_list){
            bookService.createBook(x);
        }
        List<Books> list_of_books = bookService.readAllBooks();
        Assert.assertEquals(expected_list.size(), list_of_books.size());
        Assert.assertTrue(expected_list.contains(list_of_books.get(0)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(1)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(2)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(3)));
        Assert.assertTrue(expected_list.contains(list_of_books.get(4)));
        for(Books x : expected_list){
            bookService.deleteBook(x);
        }
    }

//
//    @Test
//    public void testUpdateBooksAmount() {
//    }
//
//    @Test
//    public void testUpdateBooksTitle() {
//    }
//
//    @Test
//    public void testUpdateBooksAuthor() {
//    }
//
//    @Test
//    public void testUpdateBooksGenre() {
//    }
//
//    @Test
//    public void testUpdateBooksPubHouse() {
//    }
//
//    @Test
//    public void testUpdateBooksPubYear() {
//    }
//
//    @Test
//    public void testUpdateBooksNumPages() {
//    }
//
//    @Test
//    public void testUpdateBooksCoverType() {
//    }
}