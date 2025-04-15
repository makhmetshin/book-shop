package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.dao.OrderDao;
import ru.ifellow.jschool.machmetshin.entity.Order;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class OrderService {

    private OrderDao orderDao;


    public Set<Order> findByCustomerFio(String customerFio) {
        return orderDao.findByCustomerFio(customerFio);
    }

    public void save(Order order) {
        orderDao.save(order);
    }

    public Optional<Order> findById(Integer id) {
        return orderDao.findById(id);
    }


}
