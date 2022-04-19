package ru.webprak.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Orders {

    public Orders() {}

    public Orders(int customer_id, java.sql.Date order_time, java.sql.Date return_time, int book_id) {
        this.customer_id = customer_id;
        this.order_time = order_time;
        this.return_time = return_time;
        this.book_id = book_id;
    }

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public java.sql.Date getReturn_time() {
        return return_time;
    }

    public void setReturn_time(java.sql.Date return_time) { this.return_time = return_time;}

    public java.sql.Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(java.sql.Date order_time) {
        this.order_time = order_time;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    @Column(name = "book_id")private int book_id;


    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Column(name = "customer_id")
    private int customer_id;
    @Column(name = "return_time") private java.sql.Date return_time;
    @Column(name = "order_time") private java.sql.Date order_time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return order_id == orders.order_id &&
                book_id == (orders.book_id) &&
                customer_id == (orders.customer_id) &&
                return_time.equals(orders.return_time) &&
                order_time.equals(orders.order_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, order_id, customer_id, return_time, order_time);
    }
}
