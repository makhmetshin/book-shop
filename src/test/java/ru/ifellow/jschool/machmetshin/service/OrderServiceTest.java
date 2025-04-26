package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ifellow.jschool.machmetshin.database.repository.OrderRepository;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;


    @InjectMocks
    private OrderService orderService;

    private List<Order> orders = new ArrayList<>();

    @BeforeEach
    public void setup() {
        for (int i = 0; i < 11; i++)
            orders.add(new Order());
    }

    @Test
    public void saveTest() {

        Mockito.doReturn(new Order()).when(orderRepository).save(Mockito.any(Order.class));
        Mockito.doReturn(orders).when(orderRepository).findAll();

        orderService.save(new Order());
        Assertions.assertThat(orderService.findAll()).hasSize(11);
        Mockito.verify(orderRepository, Mockito.times(1))
                .save(Mockito.any(Order.class));
    }

    @Test
    public void findAllTest() {
        Mockito.doReturn(orders).when(orderRepository).findAll();
        Assertions.assertThat(orderService.findAll()).hasSize(11);
    }

    @Test
    public void findByIdTest() {
        Mockito.doReturn(Optional.ofNullable(Order.builder().id(1).build()) )
                .when(orderRepository).findById(1);

        Assertions.assertThat(orderService.findById(1).get().getId()).isEqualTo(1);
    }
}
