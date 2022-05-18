package ru.webprak.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.webprak.Dao.CustomersDao;
import org.springframework.ui.Model;
import ru.webprak.Models.Customers;
import ru.webprak.Services.CustomersService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class CustomerController {
    //@Autowired
    private final CustomersService customersService = new CustomersService();

    @PostMapping("/delete_customer")
    public String deleteCustomer(@RequestParam(name = "id") int id){
        customersService.deleteCustomer(customersService.readCustomerByID(id));
        return "redirect:/customers";
    }

    @GetMapping("/add_customer")
    public String addCustomer(@RequestParam(name = "id", required = false) Integer id,
                              Model model){
        model.addAttribute("id", id);
        model.addAttribute("customerService", customersService);
        return "add_customer";
    }


    @PostMapping("/add_customer")
    public String addCustomer(@RequestParam(name = "id", required = false) Integer id,
                                @RequestParam(name = "customerName") String name,
                                 @RequestParam(name = "customerLastname") String lastname,
                                 @RequestParam(name = "customerAddress", required = false) String address,
                                 @RequestParam(name = "customerEmail", required = false) String email,
                                 @RequestParam(name = "customerPhone", required = false) String phone){
        Customers res;
        if(id == null) {
            Customers customer = new Customers(lastname, name, phone, address, email);
            customersService.createCustomer(customer);
            res = customer;
        }
        else{
            Customers customer = customersService.readCustomerByID(id);
            if(!Objects.equals(address, ""))
                customer.setAddress(address);
            if(!Objects.equals(email, ""))
                customer.setMail(email);
            if(!Objects.equals(phone, ""))
                customer.setPhone_number(phone);
            customersService.updateCustomer(customer);
            res = customer;
        }
        return String.format("redirect:/customer?id=%d", res.getCustomer_id());
    }

    @GetMapping("/customer")
    public String getCustomer(@RequestParam(name = "id") int id, Model model){
        Customers customer = customersService.readCustomerByID(id);
        if(customer == null){
            model.addAttribute("error_msg", "Нет читателя с ID = " + id);
            return "error";
        }
        model.addAttribute("customer", customer);
        model.addAttribute("customersService", customersService);
        return "customer";
    }

    @PostMapping("/customers")
    public String getCustomers(@RequestParam(name = "lastName", required = false) String lastName,
                               @RequestParam(name = "firstName", required = false) String firstName,
                               @RequestParam(name = "id", required = false) Integer id){
        String res = "redirect:/customers?";
        if(lastName != null || firstName != null || id != null){
            if(!Objects.equals(lastName, ""))
                res = res + "lastName=" + lastName + "&";
            if(!Objects.equals(firstName, ""))
                res = res + "firstName=" + firstName + "&";
            if(id != null)
                res = res + "id=" + id;
        }
        return res;
    }

    @GetMapping("/customers")
    public String getCustomers(@RequestParam(name = "firstName" , required = false) String firstName,
                               @RequestParam(name = "lastName" , required = false) String lastName,
                               @RequestParam(name = "id" , required = false) Integer id,
                               Model model)
    {
        List <Customers> customers = customersService.readCustomers();
        if(id != null){
            Customers tmp = customersService.readCustomerByID(id);
            List <Customers> tmps = new ArrayList<Customers>();
            tmps.add(tmp);
            customers.retainAll(tmps);
        }
        if(firstName != null){
            List <Customers> tmp = customersService.readCustomersByFirstName(firstName);
            customers.retainAll(tmp);
        }
        if(lastName != null){
            List <Customers> tmp = customersService.readCustomersByLastName(lastName);
            customers.retainAll(tmp);
        }
        model.addAttribute("customers", customers);
        model.addAttribute("customersService", customersService);
        return "customers";
    }
}