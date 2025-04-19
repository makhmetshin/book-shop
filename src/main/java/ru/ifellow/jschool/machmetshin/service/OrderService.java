package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import ru.ifellow.jschool.machmetshin.database.repository.OrderRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Order;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class OrderService {

    private OrderRepository orderRepository;


//    public Set<Order> findByCustomerFio(String customerFio) {
//        return orderRepository.findByCustomerFio(customerFio);
//    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }


}
