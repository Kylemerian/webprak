package ru.webprak.Dao;

import ru.webprak.Models.Customers;

import java.util.List;

public interface CustomersDao {
    void create(Customers customer);
    void update(Customers customer);
    void delete(Customers customer);
    Customers readByID(int id);
    List<Customers> readCustomers();
}
