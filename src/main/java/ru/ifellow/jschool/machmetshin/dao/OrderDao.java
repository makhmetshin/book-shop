package ru.ifellow.jschool.machmetshin.dao;

import ru.ifellow.jschool.machmetshin.database.DataSource;
import ru.ifellow.jschool.machmetshin.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderDao {

    private DataSource dataSource;
    private OrderDao() {}

    @Autowired
    public OrderDao (DataSource dataSource) {
        this.dataSource = dataSource;
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
