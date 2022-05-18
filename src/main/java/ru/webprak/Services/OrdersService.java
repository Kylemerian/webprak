package ru.webprak.Services;

import ru.webprak.Dao.Impl.OrdersDaoImpl;
import ru.webprak.Dao.OrdersDao;
import ru.webprak.Models.Customers;
import ru.webprak.Models.Orders;

import java.util.List;

public class OrdersService {
    private final OrdersDao ordersDao = new OrdersDaoImpl();
    public void createOrder(Orders order) {
        ordersDao.create(order);
    }

    public List<Orders> readOrdersByCustomerId(int customer) {
        return ordersDao.readOrdersById(customer);
    }

    public List<Orders> readOrders() {
        return ordersDao.readOrders();
    }

    public void deleteOrder(Orders order) {
        ordersDao.delete(order);
    }

    public void updateOrder(Orders order) {
        ordersDao.update(order);
    }

    public Orders readOrderByID(int id) {
        return ordersDao.readByID(id);
    }
    public List<Orders> readOrdersByUniqueBookId(int uniq){return  ordersDao.readOrdersByUniqueBookId(uniq);}
}
