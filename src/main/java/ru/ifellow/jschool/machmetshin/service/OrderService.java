package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.database.repository.OrderRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;

import java.util.List;
import java.util.Optional;


@NoArgsConstructor
@AllArgsConstructor
@Service
public class OrderService implements Findable<Integer, Order> {

    private OrderRepository orderRepository;


    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
