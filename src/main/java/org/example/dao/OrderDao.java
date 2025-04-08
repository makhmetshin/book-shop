package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Order;
import org.example.entity.ShopBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderDao {

    private DataSource dataSource;

    private Set<Order> orders;
    private OrderDao() {}

    @Autowired
    public OrderDao (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static OrderDao getInstance() {
        return new OrderDao();
//        return INSTANCE;
    }
    public Optional<Order> findById(Integer id) {

        return dataSource.getOrders().stream()
                .filter(it -> it.getOrderId().equals(id))
                .findFirst();
    }
    public void save(Order order) {
        dataSource.getOrders().add(order);
    }
    public Set<Order> findAll() {
        return dataSource.getOrders();
    }

    public Set<Order> findByCustomerFio(String customerFio) {

        return dataSource.getOrders().stream()
                .filter(it -> it.getCustomerFio().equals(customerFio))
                .collect(Collectors.toSet());
    }

}
