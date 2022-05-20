package ru.webprak.Dao;

import ru.webprak.Models.Customers;
import org.junit.*;
import ru.webprak.Services.CustomersService;

import java.util.List;
import java.util.Objects;
import java.util.Set;


public class CustomersServiceTest {

    @Test
    public void testCreateCustomer() {
        CustomersService customersService = new CustomersService();
        Customers new_customer = new Customers("name1", "fname1", "Москва", "790852258741", "mail@mail.ru");
        customersService.createCustomer(new_customer);

        Customers check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertEquals(new_customer, check_customer);

        customersService.deleteCustomer(new_customer);
    }

    @Test
    public void testDeleteCustomer() {
        CustomersService customersService = new CustomersService();
        Customers new_customer = new Customers("name1", "fname1", "Москва", "790852258741", "mail@mail.ru");
        customersService.createCustomer(new_customer);

        Customers check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertEquals(new_customer, check_customer);

        customersService.deleteCustomer(new_customer);
        check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertNull(check_customer);
    }

    @Test
    public void testUpdateCustomer() {
        CustomersService customersService = new CustomersService();
        Customers new_customer = new Customers("name1", "fname1", "Москва", "790852258741", "mail@mail.ru");
        customersService.createCustomer(new_customer);

        Customers check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertEquals(new_customer, check_customer);

        new_customer.setMail("ya@ya.ru");
        customersService.updateCustomer(new_customer);
        check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertEquals(new_customer, check_customer);
        customersService.deleteCustomer(new_customer);
    }

    @Test
    public void testReadCustomerByID() {
        CustomersService customersService = new CustomersService();
        Customers new_customer = new Customers("name1", "fname1", "Москва", "790852258741", "mail@mail.ru");
        customersService.createCustomer(new_customer);

        Customers check_customer = customersService.readCustomerByID(new_customer.getCustomer_id());
        Assert.assertEquals(new_customer.getCustomer_id(), check_customer.getCustomer_id());
        customersService.deleteCustomer(new_customer);
    }

    @Test
    public void testReadAllCustomers() {
        CustomersService customersService = new CustomersService();
        Set<Customers> expected_list = Set.of(
                new Customers("name", "fname1", "Москва1", "790852258743", "mail@mail.ru"),
                new Customers("name", "fname1", "Москва2", "790852258743", "mail@mail.ru"),
                new Customers("name", "fname1", "Москва3", "790852258743", "mail@mail.ru"),
                new Customers("name", "fname1", "Москва4", "790852258743", "mail@mail.ru"),
                new Customers("name", "fname1", "Москва5", "790852258743", "mail@mail.ru")
        );
        for(Customers x: expected_list)
            customersService.createCustomer(x);
        List<Customers> list_of_customers = customersService.readCustomers();

        Assert.assertEquals(list_of_customers.size(), expected_list.size());
        /*Assert.assertTrue(expected_list.contains(list_of_customers.get(0)));
        Assert.assertTrue(expected_list.contains(list_of_customers.get(1)));
        Assert.assertTrue(expected_list.contains(list_of_customers.get(2)));
        Assert.assertTrue(expected_list.contains(list_of_customers.get(3)));
        Assert.assertTrue(expected_list.contains(list_of_customers.get(4)));*/

        for(Customers x: expected_list)
            customersService.deleteCustomer(x);
    }

    @Test
    public void testReadCustomersByLastName(){
        CustomersService customersService = new CustomersService();
        Set<Customers> expected_list = Set.of(
                new Customers("name", "fname1", "Москва1", "790852258743", "mail@mail.ru"),
                new Customers("name2", "fname1", "Москва2", "790852258743", "mail@mail.ru"),
                new Customers("name2", "fname1", "Москва3", "790852258743", "mail@mail.ru"),
                new Customers("name", "fname1", "Москва4", "790852258743", "mail@mail.ru"),
                new Customers("name", "fname1", "Москва5", "790852258743", "mail@mail.ru")
        );
        for(Customers x: expected_list)
            if(Objects.equals(x.getLastname(), "name2"))
                customersService.createCustomer(x);
        List<Customers> list_of_customers = customersService.readCustomersByLastName("name2");

        Assert.assertEquals(list_of_customers.size(), 2);
        for(Customers x: expected_list)
            if(Objects.equals(x.getLastname(), "name2"))
                customersService.deleteCustomer(x);
    }

    @Test
    public void testReadCustomersByFirstName(){
        CustomersService customersService = new CustomersService();
        Set<Customers> expected_list = Set.of(
                new Customers("name", "fname1", "Москва1", "790852258743", "mail@mail.ru"),
                new Customers("name2", "fname2", "Москва2", "790852258743", "mail@mail.ru"),
                new Customers("name2", "fname2", "Москва3", "790852258743", "mail@mail.ru"),
                new Customers("name", "fname1", "Москва4", "790852258743", "mail@mail.ru"),
                new Customers("name", "fname1", "Москва5", "790852258743", "mail@mail.ru")
        );
        for(Customers x: expected_list)
            if(Objects.equals(x.getFirstname(), "fname2"))
                customersService.createCustomer(x);
        List<Customers> list_of_customers = customersService.readCustomersByFirstName("fname2");

        Assert.assertEquals(list_of_customers.size(), 2);
        for(Customers x: expected_list)
            if(Objects.equals(x.getFirstname(), "fname2"))
                customersService.deleteCustomer(x);
    }
}