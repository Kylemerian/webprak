package ru.webprak.Services;

import ru.webprak.Dao.CustomersDao;
import ru.webprak.Dao.Impl.CustomersDaoImpl;
import ru.webprak.Models.Customers;
import ru.webprak.Models.Orders;

import java.util.List;

public class CustomersService {
    private final CustomersDao customersDao = new CustomersDaoImpl();
    public void createCustomer(Customers customer) {
        customersDao.create(customer);
    }

    public void deleteCustomer(Customers customer) {
        customersDao.delete(customer);
    }

    public void updateCustomer(Customers customer) {
        customersDao.update(customer);
    }

    public Customers readCustomerByID(int id) {
        return customersDao.readByID(id);
    }

    public List<Customers> readCustomers() {
        return customersDao.readCustomers();
    }
}
