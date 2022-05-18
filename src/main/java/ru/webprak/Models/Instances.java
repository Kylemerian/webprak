package ru.webprak.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "instances")
public class Instances {
    public Instances(int book_id, int instance_id, boolean is_free) {
        this.book_id = book_id;
        this.instance_id = instance_id;
        this.is_free = is_free;
    }
    public Instances(){}
    @Id
    @Column(name = "unique_book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unique_book_id;

    @Column(name = "book_id") private int book_id;
    @Column(name = "instance_id") private int instance_id;
    @Column(name = "is_free") private boolean is_free;

    public int getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(int instance_id) {
        this.instance_id = instance_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUnique_book_id() {
        return unique_book_id;
    }

    public void setUnique_book_id(int unique_book_id) {
        this.unique_book_id = unique_book_id;
    }

    public void setIs_free(boolean p){ this.is_free = p;}
    public boolean getIs_free(){return is_free;};

    @Override
    public int hashCode() {
        return Objects.hash(book_id, instance_id, unique_book_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != this.getClass()) { return false; }
        final Instances other = (Instances) obj;
        return (this.book_id == other.book_id) &&
                (this.unique_book_id == other.unique_book_id) &&
                (this.instance_id == other.instance_id) &&
                (this.is_free == other.is_free);
    }
}
