package ru.ifellow.jschool.machmetshin.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.order.Order;

import java.util.Set;

@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void findAllTest() {
        Assertions.assertThat(orderRepository.findAll()).hasSize(10);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(orderRepository.findById(1).get().getTotalPrice()).isEqualTo(1050);
        Assertions.assertThat(orderRepository.findById(5).get().getTotalPrice()).isEqualTo(700);
        Assertions.assertThat(orderRepository.findById(10).get().getTotalPrice()).isEqualTo(560);
    }

    @Test
    public void findBySaveTest() {
        orderRepository.save(new Order());
        Assertions.assertThat(orderRepository.findAll()).hasSize(11);
    }

    @Test
    public void deleteTest() {
        Order order = orderRepository.findById(1).get();
        orderRepository.delete(order);
        Assertions.assertThat(orderRepository.findAll()).hasSize(9);
    }

    @Test
    public void findByUserIdTest() {
        Assertions.assertThat(orderRepository.findByUserId(1)).hasSize(1);
        Assertions.assertThat(orderRepository.findByUserId(5)).hasSize(1);
        Assertions.assertThat(orderRepository.findByUserId(10)).hasSize(1);
    }
}
