package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.ifellow.jschool.machmetshin.dao.BookDao;
import ru.ifellow.jschool.machmetshin.dao.OrderDao;
import ru.ifellow.jschool.machmetshin.dao.WarehouseBookDao;
import ru.ifellow.jschool.machmetshin.entity.Book;
import ru.ifellow.jschool.machmetshin.entity.Order;
import ru.ifellow.jschool.machmetshin.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class WebShopService {

    private int orderIdSequence = 1;

    private WarehouseBookService warehouseBookService;
    private BookService bookService;
    private OrderService orderService;

    public Set<Order> findOrdersByFio(String customerFio) {
        return orderService.findByCustomerFio(customerFio);
    }

    public Order createOrder(String customerFio, Integer warehouseId, Integer arrivalShopId, Map<Integer, Integer> orderItems ) {

        int totalPrice = 0;
        LocalDate today = LocalDate.now();

        for( Map.Entry<Integer, Integer> entry : orderItems.entrySet() ) {
            Integer bookId = entry.getKey();
            Integer amount = entry.getValue();
            if (warehouseBookService.getAmountOfBook(bookId, warehouseId) < amount ) {
                System.out.println("Недостаточно книг c id %d на складе с id %d".formatted(bookId, warehouseId));
                return null;
            }
        }

        for( Map.Entry<Integer, Integer> entry : orderItems.entrySet() ) {
            Integer bookId = entry.getKey();
            Integer amount = entry.getValue();
            warehouseBookService.removeBook(bookId, warehouseId, amount);

            Book book = bookService.findById(bookId).get();
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
        orderService.save(order);

        return order;
    }


    public void cancelOrder(Integer orderId) {

        Order order = orderService.findById(orderId).get();
        order.setStatus(Status.CANCELLED);

        Integer warehouseId = order.getDepartureWarehouse();
        for(Map.Entry<Integer, Integer> entry : order.getItems().entrySet()) {
            Integer bookId = entry.getKey();
            Integer amount = entry.getValue();
            warehouseBookService.addBook(bookId, warehouseId, amount);
        }

    }

    public void changeOrderStatus(Status status, Integer orderId) {
        if (status == Status.CANCELLED) {
            cancelOrder(orderId);
            return;
        }
        Order order = orderService.findById(orderId).get();
        order.setStatus(status);
    }

    public void takeAwayOrder(Integer orderId) {
        changeOrderStatus(Status.FINISHED, orderId);
    }



}
