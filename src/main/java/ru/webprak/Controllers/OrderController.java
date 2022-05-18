package ru.webprak.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.webprak.Models.Books;
import ru.webprak.Models.Customers;
import ru.webprak.Models.Instances;
import ru.webprak.Models.Orders;

import ru.webprak.Services.BooksService;
import ru.webprak.Services.CustomersService;
import ru.webprak.Services.InstancesService;
import ru.webprak.Services.OrdersService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    private final OrdersService ordersService = new OrdersService();
    private final InstancesService instancesService = new InstancesService();
    private final BooksService booksService = new BooksService();
    private final CustomersService customersService = new CustomersService();

    @PostMapping(value =  "/add_order")
    public String addOrder(@RequestParam(name = "customer_id") Integer customer_id,
                         @RequestParam(name = "book_id") Integer book_id,
                         @RequestParam(name = "instance_id") Integer instance_id,
                         @RequestParam(name = "return_date") java.sql.Date return_date,
                         @RequestParam(name = "order_date") java.sql.Date order_date) {
        Orders order = new Orders(customer_id, order_date, return_date, instancesService.readByBookIdByInstanceId(book_id, instance_id).getUnique_book_id());
        Instances tmp = instancesService.readInstancesByID(order.getBook_id());
        tmp.setIs_free(false);
        Books book = booksService.readBookByID(tmp.getBook_id());
        book.setFreeAmount(book.getFreeAmount() - 1);
        booksService.updateBook(book);
        instancesService.updateInstance(tmp);
        ordersService.createOrder(order);
        return String.format("redirect:/order?id=%d", order.getOrder_id());
    }

    @GetMapping(value = "/add_order")
    public String addOrder(@RequestParam(name = "book_id", required = false) Integer book_id,
                           @RequestParam(name = "instance_id", required = false) Integer instance_id,
                           Model model) {
        if(book_id != null)
            model.addAttribute("book_id", book_id);
        if(instance_id != null)
            model.addAttribute("instance_id", instance_id);
        return "add_order";
    }

    @PostMapping(value = "/order")
    public String getOrder(@RequestParam(name = "id") Integer order_id){
        Orders order = ordersService.readOrderByID(order_id);
        Instances inst = instancesService.readInstancesByID(order.getBook_id());
        inst.setIs_free(true);
        Books book = booksService.readBookByID(inst.getBook_id());
        book.setFreeAmount(book.getFreeAmount() + 1);
        instancesService.updateInstance(inst);
        booksService.updateBook(book);
        return String.format("redirect:/order?id=%d", order_id);
    }

    @GetMapping(value = "/order")
    public String getOrder(@RequestParam(name = "id") Integer order_id, Model model){
        model.addAttribute("order", ordersService.readOrderByID(order_id));
        model.addAttribute("instanceService", instancesService);
        model.addAttribute("bookService", booksService);
        return "/order";
    }

    @PostMapping(value = "/orders")
    public String getOrders(@RequestParam(name = "customer_id", required = false) Integer customer_id,
                            @RequestParam(name = "order_id", required = false) Integer order_id,
                            @RequestParam(name = "book_id", required = false) Integer book_id,
                            @RequestParam(name = "instance_id", required = false) Integer instance_id
                            ){
        String res = "redirect:/orders?";
        if(customer_id != null)
            res = res + "customer_id=" + customer_id + "&";
        if(order_id != null)
            res = res + "order_id=" + order_id + "&";
        if(book_id != null)
            res = res + "book_id=" + book_id + "&";
        if(instance_id != null)
            res = res + "instance_id=" + instance_id + "&";
        return res;
    }

    @GetMapping(value =  "/orders")
    public String getOrders(@RequestParam(name = "customer_id", required = false) Integer customer_id,
                            @RequestParam(name = "order_id", required = false) Integer order_id,
                            @RequestParam(name = "book_id", required = false) Integer book_id,
                            @RequestParam(name = "instance_id", required = false) Integer instance_id,
                            Model model)
    {
        List<Orders> orders = ordersService.readOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("customersService", customersService);
        model.addAttribute("booksService", booksService);
        model.addAttribute("instancesService", instancesService);
        model.addAttribute("ordersService", ordersService);
        if(customer_id != null) {
            orders.retainAll(ordersService.readOrdersByCustomerId(customer_id));
        }
        if(order_id != null){
            List<Orders> tmps = new ArrayList<>();
            Orders tmp = ordersService.readOrderByID(order_id);
            if(!tmps.isEmpty())
                tmps.add(tmp);
            orders.retainAll(tmps);
        }
        if(book_id != null && instance_id != null){
            if(booksService.readBookByID(book_id) == null || instancesService.readByBookIdByInstanceId(book_id, instance_id) == null){
                orders.clear();
                return "/orders";
            }
            orders.retainAll(ordersService.readOrdersByUniqueBookId(instancesService.readByBookIdByInstanceId(book_id, instance_id).getUnique_book_id()));
        }
        return "orders";
    }

    @PostMapping(value = "/delete_order")
    public String deleteOrder(@RequestParam(name = "id") Integer order_id){
        ordersService.deleteOrder(ordersService.readOrderByID(order_id));
        return "redirect:/orders";
    }
}
