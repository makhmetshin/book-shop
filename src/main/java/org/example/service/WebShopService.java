package org.example.service;

import org.example.dao.*;
import org.example.entity.Book;
import org.example.entity.Order;
import org.example.entity.Status;
import org.example.entity.WarehouseBook;

import java.time.LocalDate;
import java.util.Map;


public class WebShopService {

    private int orderIdSequence = 1;

    private ShopBookDao shopBookDao = ShopBookDao.getInstance();
    private WarehouseBookDao warehouseBookDao = WarehouseBookDao.getInstance();
    private BookDao bookDao = BookDao.getInstance();
    private OrderDao orderDao = OrderDao.getInstance();

    private final static WebShopService  INSTANCE = new WebShopService ();
    private WebShopService () {}

    public static WebShopService  getInstance() {
        return new WebShopService();
//        return INSTANCE;
    }

    public Order createOrder(String customerFio, Integer warehouseId, Integer arrivalShopId, Map<Integer, Integer> orderItems ) {

        int totalPrice = 0;
        LocalDate today = LocalDate.now();

        for( Map.Entry<Integer, Integer> entry : orderItems.entrySet() ) {
            Integer bookId = entry.getKey();
            Integer amount = entry.getValue();
            if (warehouseBookDao.getAmountOfBook(bookId, warehouseId) < amount ) {
                System.out.println("Недостаточно книг c id %d на складе с id %d".formatted(bookId, warehouseId));
                return null;
            }
        }

        for( Map.Entry<Integer, Integer> entry : orderItems.entrySet() ) {
            Integer bookId = entry.getKey();
            Integer amount = entry.getValue();
            warehouseBookDao.removeBook(bookId, warehouseId, amount);

            Book book = bookDao.findById(bookId).get();
            totalPrice += book.getPrice() * amount;
        }

        Order order = Order.builder()
                .orderId(orderIdSequence++)
                .customerFio(customerFio)
                .items(orderItems)
                .orderDate(today)
                .arrivalDate(today.plusDays(5))
                .status(Status.ASSEMBLING)
                .totalPrice(totalPrice)
                .departureWarehouse(warehouseId)
                .arrivalShopId(arrivalShopId)
                .build();
        orderDao.save(order);

        return order;
    }


    public void cancelOrder(Integer orderId) {

        Order order = orderDao.findById(orderId);
        order.setStatus(Status.CANCELLED);

        Integer warehouseId = order.getDepartureWarehouse();
        for(Map.Entry<Integer, Integer> entry : order.getItems().entrySet()) {
            Integer bookId = entry.getKey();
            Integer amount = entry.getValue();
            warehouseBookDao.addBook(bookId, warehouseId, amount);
        }

    }

    public void changeOrderStatus(Status status, Integer orderId) {
        if (status == Status.CANCELLED) {
            cancelOrder(orderId);
            return;
        }
        Order order = orderDao.findById(orderId);
        order.setStatus(status);
    }

    public void takeAwayOrder(Integer orderId) {
        changeOrderStatus(Status.FINISHED, orderId);
    }



}
