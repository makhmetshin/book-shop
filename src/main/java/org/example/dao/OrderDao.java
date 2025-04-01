package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Order;
import org.example.entity.ShopBook;

import java.util.Set;

public class OrderDao {

    private DataSource dataSource = DataSource.getInstance();

    private final static OrderDao INSTANCE = new OrderDao();
    private Set<Order> orders = dataSource.getOrders();
    private OrderDao() {}

    public static OrderDao getInstance() {
        return new OrderDao();
//        return INSTANCE;
    }
    public Order findById(Integer id) {

        return orders.stream()
                .filter(it -> it.getOrderId().equals(id))
                .findFirst()
                .orElseThrow();
    }
    public void save(Order order) {
        orders.add(order);
    }

}
