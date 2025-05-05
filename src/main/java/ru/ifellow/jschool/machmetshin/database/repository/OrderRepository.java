package ru.ifellow.jschool.machmetshin.database.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Order;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @EntityGraph(attributePaths = {"orderItems", "departureWarehouse", "arrivalShop", "user"})
    Set<Order> findByUserId(Integer userId);

    @EntityGraph(attributePaths = {"orderItems", "orderItems.good", "departureWarehouse", "arrivalShop", "user"})
    Optional<Order> findById(Integer Id);
}
