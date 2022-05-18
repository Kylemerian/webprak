package ru.webprak.Controllers;

import org.springframework.web.bind.annotation.*;
import ru.webprak.Models.Books;
import ru.webprak.Models.Customers;
import ru.webprak.Models.Instances;
import ru.webprak.Services.BooksService;
import ru.webprak.Services.CustomersService;
import ru.webprak.Services.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.webprak.Services.InstancesService;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class BookController {
    private final BooksService booksService;
    private final InstancesService instancesService;
    private final OrdersService ordersService;

    public BookController() { booksService = new BooksService(); instancesService = new InstancesService(); ordersService = new OrdersService();}


    @GetMapping(value = "/add_book")
    public String addBook(@RequestParam(name = "id", required = false) Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("bookService", booksService);
        return "add_book";
    }

    @PostMapping(value = "/delete_book")
    public String deleteBook(@RequestParam(name = "id") int id){
        booksService.deleteBook(booksService.readBookByID(id));
        return "redirect:/books";
    }

    @PostMapping(value = "/add_book")
    public String addBook(@RequestParam(name = "id", required = false) Integer id,
                          @RequestParam(name = "title") String title,
                        @RequestParam(name = "authors") String authors,
                        @RequestParam(name = "publ_year", required = false) Integer publ_year,
                        @RequestParam(name = "isbn", required = false) String isbn,
                        @RequestParam(name = "publ_house", required = false) String publ_house,
                        @RequestParam(name = "genre", required = false) String genre,
                        @RequestParam(name = "amount") Integer amount) {
        Books res;
        if(id != null){
               Books book = booksService.readBookByID(id);
               if(!Objects.equals(title, ""))
                   book.setTitle(title);
               if(!Objects.equals(authors, ""))
                   book.setAuthor(authors);
               if(publ_year != null)
                   book.setPub_year(publ_year);
               if(!Objects.equals(isbn, ""))
                   book.setIsbn(isbn);
               if(publ_house != "")
                   book.setPub_house(publ_house);
               if(!Objects.equals(genre, ""))
                   book.setGenre(genre);
               booksService.updateBook(book);
               res = book;
        }
        else {
            Books book = new Books(amount, title, authors, genre, publ_house, publ_year, amount, isbn);
            booksService.createBook(book);
            res = book;
        }
        return String.format("redirect:/book?id=%d", res.getBook_id());
    }

    @PostMapping(value = "/book")
    public String getBook(@RequestParam(name = "instanceID") Integer instanceID,
                          @RequestParam(name = "book_id") Integer book_id,
                          Model model)
    {
        if(booksService.readBookByID(book_id).getAmount() < instanceID){
            model.addAttribute("error_msg", "Нет экземпляра = " + instanceID);
            return "error";
        }
        return String.format("redirect:/book?id=%d&instance_id=%d", book_id, instanceID);
    }

    @GetMapping(value = "/book")
    public String getBook(@RequestParam(name = "id") int id,
                          @RequestParam(name = "instance_id", required = false) Integer instance_id,
                          Model model) {
        Books book = booksService.readBookByID(id);
        if(instance_id != null){
            Instances instance = instancesService.readByBookIdByInstanceId(id, instance_id);
            model.addAttribute("instance", instance);
        }
        if(book == null){
            model.addAttribute("error_msg", "Нет книги с ID = " + id);
            return "error";
        }
        model.addAttribute("book", book);
        model.addAttribute("booksService", booksService);
        return "book";
    }

    @PostMapping(value = "/books")
    public String getBooks(@RequestParam(name = "author", required = false) String author,
                           @RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "genre", required = false) String genre){
        String res = "redirect:/books?";
        if(author != null || title != null || genre != null){
            if(!Objects.equals(author, ""))
                res = res + "author=" + author + "&";
            if(!Objects.equals(title, ""))
                res = res + "title=" + title + "&";
            if(!Objects.equals(genre, ""))
                res = res + "genre=" + genre + "&";
        }
        return res;
    }


    @GetMapping(value = "/books")
    public String getBooks(@RequestParam(name = "author", required = false) String author,
                           @RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "genre", required = false) String genre,
                            Model model) {
        List <Books> books = booksService.readAllBooks();
        if(author != null){
            List <Books> tmp = booksService.readBooksListByAuthor(author);
            books.retainAll(tmp);
        }
        if(title != null){
            List <Books> tmp = booksService.readBooksListByTitle(title);
            books.retainAll(tmp);
        }
        if(genre != null){
            List <Books> tmp = booksService.readBooksListByGenre(genre);
            books.retainAll(tmp);
        }
        model.addAttribute("books", books);
        model.addAttribute("booksService", booksService);
        return "books";
    }
}