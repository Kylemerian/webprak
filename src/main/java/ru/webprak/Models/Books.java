package ru.webprak.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Books")
public class Books
{
    public Books(int amount, String title, String author,
                        String genre, String pub_house, Integer pub_year, int free_amount, String isbn) {
        this.amount = amount;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pub_house = pub_house;
        this.pub_year = pub_year;
        this.free_amount = free_amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, title, author, genre, pub_house,
                                pub_year, free_amount, isbn);
    }

    public Books() {}

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFreeAmount() {
        return free_amount;
    }

    public void setFreeAmount(int free_amount) {
        this.free_amount = free_amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPub_house() {
        return pub_house;
    }

    public void setPub_house(String pub_house) {
        this.pub_house = pub_house;
    }

    public Integer getPub_year() {
        return pub_year;
    }

    public void setPub_year(int pub_year) {
        this.pub_year = pub_year;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != this.getClass()) { return false; }
        final Books other = (Books) obj;
        return  ((this.amount == other.amount) &&
                (this.title.equals(other.title)) &&
                (this.author.equals(other.author)) &&
                (this.genre.equals(other.genre)) &&
                (this.pub_house.equals(other.pub_house)) &&
                (Objects.equals(this.pub_year, other.pub_year)) &&
                (this.isbn.equals(other.isbn)) &&
                (this.free_amount == other.free_amount));
    }
    @Column(name = "amount") private int amount;
    @Column(name = "title") private String title;
    @Column(name = "author") private String author;
    @Column(name = "genre") private String genre;
    @Column(name = "p_house") private String pub_house;
    @Column(name = "year") private Integer pub_year;
    @Column(name = "free_amount") private int free_amount;
    @Column(name = "isbn") private String isbn;
}
