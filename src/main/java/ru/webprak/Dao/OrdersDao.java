package ru.webprak.Dao;
import ru.webprak.Models.Orders;

import java.util.List;

public interface OrdersDao {
    void create(Orders order);
    void update(Orders order);
    void delete(Orders order);
    Orders readByID(int id);
    List<Orders> readOrdersById(int customer);
    List<Orders> readOrders();
}
