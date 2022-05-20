package ru.webprak.Dao;

import ru.webprak.Models.*;
import org.junit.*;
import ru.webprak.Services.BooksService;
import ru.webprak.Services.InstancesService;

import java.util.List;
import java.util.Set;


public class InstancesServiceTest {

    @Test
    public void testCreateInstance() {
        InstancesService instancesService = new InstancesService();
        BooksService bookService = new BooksService();
        Books new_book = new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323");
        bookService.createBook(new_book);
        Instances new_instance = new Instances(new_book.getBook_id(), 0, true);
        instancesService.createInstance(new_instance);
        Instances check_instance = instancesService.readInstancesByID(new_instance.getUnique_book_id());
        Assert.assertEquals(new_instance, check_instance);
        instancesService.deleteInstance(new_instance);
        bookService.deleteBook(new_book);
    }

    @Test
    public void testDeleteInstance() {
        InstancesService instancesService = new InstancesService();
        Instances new_instance = new Instances(1, 0, true);
        instancesService.createInstance(new_instance);

        Instances check = instancesService.readInstancesByID(new_instance.getUnique_book_id());
        Assert.assertEquals(new_instance, check);

        instancesService.deleteInstance(new_instance);
        check = instancesService.readInstancesByID(new_instance.getUnique_book_id());
        Assert.assertNull(check);
    }

    @Test
    public void testUpdateInstance() {
        InstancesService instancesService = new InstancesService();
        Instances new_instance = new Instances(1, 0, true);
        instancesService.createInstance(new_instance);

        Instances check_instance = instancesService.readInstancesByID(new_instance.getUnique_book_id());
        Assert.assertEquals(new_instance, check_instance);

        new_instance.setInstance_id(1);
        instancesService.updateInstance(new_instance);
        check_instance = instancesService.readInstancesByID(new_instance.getUnique_book_id());
        Assert.assertEquals(new_instance, check_instance);
        instancesService.deleteInstance(new_instance);
    }

    @Test
    public void testReadInstanceByID() {
        InstancesService instancesService = new InstancesService();
        BooksService bookService = new BooksService();
        Books new_book = new Books(40,  "Война и мир 1", "Л.Н. Толстой", "Роман", "Эксмо 0", 2011, 30, "12323");
        bookService.createBook(new_book);
        Instances new_instance = new Instances(new_book.getBook_id(), 0, true);
        instancesService.createInstance(new_instance);
        Instances check_instance = instancesService.readInstancesByID(new_instance.getUnique_book_id());
        Assert.assertEquals(new_instance.getUnique_book_id(), check_instance.getUnique_book_id());
        instancesService.deleteInstance(new_instance);
        bookService.deleteBook(new_book);
    }

    @Test
    public void testReadByBookIdByInstanceId(){
        InstancesService instancesService = new InstancesService();

        Instances test = new Instances(1, 2, true);

        instancesService.createInstance(test);
        Instances list_of_instances = instancesService.readByBookIdByInstanceId(1, 2);
        Assert.assertEquals(list_of_instances, test);

        instancesService.deleteInstance(test);
    }

    @Test
    public void testReadInstanceByBookId(){
        InstancesService instancesService = new InstancesService();
        Set<Instances> expected_list = Set.of(
                new Instances(1, 2, true),
                new Instances(2, 3, true),
                new Instances(2, 4, true),
                new Instances(3, 5, true)
        );
        for(Instances x : expected_list)
            if(x.getBook_id() == 2)
                instancesService.createInstance(x);
        Books book = new Books(50, "a", "a", "a", "a", 2022, 2, "sdf");
        book.setBook_id(2);
        List<Instances> list_of_instances = instancesService.readInstancesByBookId(book);

        Assert.assertEquals(list_of_instances.size(), 2);

        for(Instances x : expected_list)
            if(x.getBook_id() == 2)
                instancesService.deleteInstance(x);
    }

    @Test
    public void testReadInstances() {
        InstancesService instancesService = new InstancesService();
        Set<Instances> expected_list = Set.of(
                new Instances(1, 2, true),
                new Instances(1, 3, true),
                new Instances(1, 4, true),
                new Instances(1, 5, true)
        );
        for(Instances x : expected_list)
            instancesService.createInstance(x);
        List<Instances> list_of_instances = instancesService.readInstances();

        Assert.assertEquals(list_of_instances.size(), expected_list.size());

        for(Instances x : expected_list)
            instancesService.deleteInstance(x);
    }
}