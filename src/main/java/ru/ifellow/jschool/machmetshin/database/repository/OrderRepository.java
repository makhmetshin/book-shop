package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Order;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Set<Order> findByCustomerFio(String customerFio);
}
