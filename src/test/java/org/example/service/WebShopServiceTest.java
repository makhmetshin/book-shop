package org.example.service;

import org.example.dao.*;
import org.example.entity.Book;
import org.example.entity.Order;
import org.example.entity.Status;
import org.example.entity.WarehouseBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class WebShopServiceTest {

    WebShopService webShopService = WebShopService.getInstance();


    @Test
    public void createOrderTest() {
        Map<Integer, Integer> books = new HashMap<>();
        books.put(1, 10);
        books.put(5, 20);
        books.put(10, 100);
        Order order = webShopService.createOrder("ivan", 1, 1, books );
        assertThat(order.getItems().size()).isEqualTo(3);
        assertThat(order.getStatus()).isEqualTo(Status.ASSEMBLING);

    }

    @Test
    public void cancelOrderTest() {
        Map<Integer, Integer> books = new HashMap<>();
        books.put(1, 10);
        books.put(5, 20);
        books.put(10, 100);
        Order order = webShopService.createOrder("ivan", 1, 1, books );

        webShopService.cancelOrder(order.getOrderId());
        assertThat(order.getStatus()).isEqualTo(Status.CANCELLED);
    }

    @Test
    public void changeOrderStatusTest() {
        Map<Integer, Integer> books = new HashMap<>();
        books.put(1, 10);
        books.put(5, 20);
        books.put(10, 100);
        Order order = webShopService.createOrder("ivan", 1, 1, books );

        webShopService.changeOrderStatus(Status.TRANSIT, order.getOrderId());
        assertThat(order.getStatus()).isEqualTo(Status.TRANSIT);
    }

    @Test
    public void takeAwayOrderTest() {
        Map<Integer, Integer> books = new HashMap<>();
        books.put(1, 10);
        books.put(5, 20);
        books.put(10, 100);
        Order order = webShopService.createOrder("ivan", 1, 1, books );

        webShopService.takeAwayOrder(order.getOrderId());
        assertThat(order.getStatus()).isEqualTo(Status.FINISHED);
    }


}
