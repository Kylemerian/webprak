package ru.webprak.Services;

import ru.webprak.Models.Customers;
import org.junit.*;

import java.util.List;
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
                new Customers("name1", "fname1", "Москва", "790852258743", "mail@mail.ru"),
                new Customers("name2", "fname1", "Москва", "790852258743", "mail@mail.ru"),
                new Customers("name3", "fname1", "Москва", "790852258743", "mail@mail.ru"),
                new Customers("name4", "fname1", "Москва", "790852258743", "mail@mail.ru"),
                new Customers("name5", "fname1", "Москва", "790852258743", "mail@mail.ru")
        );
        List<Customers> list_of_customers = customersService.readCustomers();
        Assert.assertEquals(list_of_customers.size(), expected_list.size());
        /*Assert.assertTrue(expected_list.contains(list_of_customers.get(0)));
        Assert.assertTrue(expected_list.contains(list_of_customers.get(1)));
        Assert.assertTrue(expected_list.contains(list_of_customers.get(2)));
        Assert.assertTrue(expected_list.contains(list_of_customers.get(3)));
        Assert.assertTrue(expected_list.contains(list_of_customers.get(4)));*/
    }
}