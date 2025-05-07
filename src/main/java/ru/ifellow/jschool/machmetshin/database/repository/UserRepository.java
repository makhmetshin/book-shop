package ru.ifellow.jschool.machmetshin.database.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ifellow.jschool.machmetshin.entity.user.User;

import java.util.List;
import java.util.Optional;
//orders.orderItems, orders.orderItems.good, orders.arrivalShop, orders.departureWarehouse
public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(attributePaths = {"orders"})
    List<User> findAll();

    @EntityGraph(attributePaths = {"orders", "orders.arrivalShop"})
    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);
}
