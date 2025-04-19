package ru.ifellow.jschool.machmetshin.database.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Order;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Set<Order> findByUserId(Integer userId);
}
