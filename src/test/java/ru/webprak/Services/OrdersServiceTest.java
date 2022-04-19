package ru.webprak.Services;

import ru.webprak.Models.Books;
import ru.webprak.Models.Customers;
import ru.webprak.Models.Orders;
import org.junit.*;

import java.util.List;
import java.util.Set;

public class OrdersServiceTest {

    @Test
    public void testCreateOrder() {
        CustomersService customersService = new CustomersService();
        OrdersService ordersService = new OrdersService();
        Customers new_customer = new Customers("name1", "fname1", "Москва", "790852258743", "mail@mail.ru");

        BooksService bookService = new BooksService();
        Books new_book = new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323");
        bookService.createBook(new_book);

        customersService.createCustomer(new_customer);

        Orders new_order = new Orders(new_customer.getCustomer_id(),
                java.sql.Date.valueOf("2020-02-17"), java.sql.Date.valueOf("2020-02-16"), new_book.getBook_id());

        ordersService.createOrder(new_order);
        Orders check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertEquals(new_order, check_order);
        ordersService.deleteOrder(new_order);
        customersService.deleteCustomer(new_customer);
    }

    @Test
    public void testDeleteOrder() {
        CustomersService customersService = new CustomersService();
        OrdersService ordersService = new OrdersService();
        Customers new_customer = new Customers("name1", "fname1", "Москва", "790852258743", "mail@mail.ru");
        BooksService bookService = new BooksService();
        Books new_book = new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323");
        bookService.createBook(new_book);
        customersService.createCustomer(new_customer);
        Orders new_order = new Orders(new_customer.getCustomer_id(),
                java.sql.Date.valueOf("2020-02-17"), java.sql.Date.valueOf("2020-02-16"), new_book.getBook_id());

        ordersService.createOrder(new_order);
        Orders check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertEquals(new_order, check_order);

        ordersService.deleteOrder(new_order);
        customersService.deleteCustomer(new_customer);
        check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertNull(check_order);
    }

    @Test
    public void testUpdateOrder() {
        CustomersService customersService = new CustomersService();
        OrdersService ordersService = new OrdersService();
        Customers new_customer = new Customers("name1", "fname1", "Москва", "790852258743", "mail@mail.ru");
        BooksService bookService = new BooksService();
        Books new_book = new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323");
        bookService.createBook(new_book);
        customersService.createCustomer(new_customer);
        Orders new_order = new Orders(new_customer.getCustomer_id(),
                java.sql.Date.valueOf("2020-02-17"), java.sql.Date.valueOf("2020-02-16"), new_book.getBook_id());

        ordersService.createOrder(new_order);
        Orders check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertEquals(new_order, check_order);

        new_order.setReturn_time(java.sql.Date.valueOf("2021-01-17"));
        ordersService.updateOrder(new_order);

        check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertEquals(new_order, check_order);
        ordersService.deleteOrder(new_order);
        customersService.deleteCustomer(new_customer);
    }

    @Test
    public void testReadOrderByID() {
        CustomersService customersService = new CustomersService();
        OrdersService ordersService = new OrdersService();
        Customers new_customer = new Customers("name1", "fname1", "Москва", "790852258743", "mail@mail.ru");
        BooksService bookService = new BooksService();
        Books new_book = new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323");
        bookService.createBook(new_book);
        customersService.createCustomer(new_customer);
        Orders new_order = new Orders(new_customer.getCustomer_id(),
                java.sql.Date.valueOf("2020-02-17"), java.sql.Date.valueOf("2020-02-16"), new_book.getBook_id());

        ordersService.createOrder(new_order);
        Orders check_order = ordersService.readOrderByID(new_order.getOrder_id());
        Assert.assertEquals(new_order.getOrder_id(), check_order.getOrder_id());
        ordersService.deleteOrder(new_order);
        customersService.deleteCustomer(new_customer);

    }

    @Test
    public void testReadOrders() {
        CustomersService customersService = new CustomersService();
        OrdersService ordersService = new OrdersService();
        Customers new_customer = new Customers("name1", "fname1", "Москва", "790852258743", "mail@mail.ru");
        new_customer.setCustomer_id(0);
        BooksService bookService = new BooksService();
        Books new_book = new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323");
        new_book.setBook_id(0);
        bookService.createBook(new_book);
        customersService.createCustomer(new_customer);

        Set<Orders> expected_list = Set.of(
                new Orders(new_customer.getCustomer_id(), java.sql.Date.valueOf("2020-02-17"), java.sql.Date.valueOf("2020-02-16"), new_book.getBook_id()),
                new Orders(new_customer.getCustomer_id(), java.sql.Date.valueOf("2019-02-17"), java.sql.Date.valueOf("2020-02-16"), new_book.getBook_id())
        );
        List<Orders> list_of_orders = ordersService.readOrders();
        Assert.assertEquals(list_of_orders.size(), expected_list.size());
        Assert.assertTrue(expected_list.contains(list_of_orders.get(0)));
        Assert.assertTrue(expected_list.contains(list_of_orders.get(1)));
    }
}