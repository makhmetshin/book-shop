package ru.ifellow.jschool.machmetshin.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.order.OrderItem;
import ru.ifellow.jschool.machmetshin.entity.order.Status;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;
import ru.ifellow.jschool.machmetshin.entity.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class WebShopManagerService {

    private WarehouseBookService warehouseBookService;
    private BookService bookService;
    private OrderService orderService;
    private WarehouseService warehouseService;
    private ShopService shopService;
    private BillService billService;
    private UserService userService;

//    public Set<Order> findOrderByFio(String customerFio) {
//        return orderService.findByCustomerFio(customerFio);
//    }

    @Transactional
    public Order createOrder(Integer userId, Integer warehouseId, Integer arrivalShopId, List<OrderItem> orderItems ) {

        int totalPrice = 0;
        LocalDate today = LocalDate.now();

        for( OrderItem orderItem : orderItems ) {
            Integer itemId = orderItem.getId();
            Integer amount = orderItem.getQuantity();

            if (warehouseBookService.getAmountOfBook(itemId, warehouseId) < amount ) {
                System.out.println("Недостаточно книг c id %d на складе с id %d".formatted(itemId, warehouseId));
                return null;
            }
        }

        // А зачем второй раз проходимся по списку, можно и за один раз всё сделать?
        // Для прерывания и отката транзакции можно попробовать исключение выбросить
        for( OrderItem orderItem : orderItems ) {
            Integer bookId = orderItem.getId(); //аналогичная ошибка, айди позиции заказа - это же не айди книги?
            Integer amount = orderItem.getQuantity();
            warehouseBookService.removeBook(bookId, warehouseId, amount);

            Book book = bookService.findById(bookId).get();
            totalPrice += book.getPrice() * amount;
        }
        User user = userService.findById(userId).get();

        Order order = Order.builder()
                .user(user)
                .orderItems(orderItems.stream().collect(Collectors.toSet()) )
                .orderDate(today)
                .arrivalDate(today.plusDays(5))
                .status(Status.ASSEMBLING)
                .totalPrice(totalPrice)
                .departureWarehouse(warehouseService.findById(warehouseId).orElseThrow(
                        () -> new EntityNotFoundException("Warehouse not found")
                        ))
                .arrivalShop(shopService.findById(arrivalShopId).orElseThrow(
                        () -> new EntityNotFoundException("Shop not found")
                        ))
                .build();

        orderService.save(order);

        return order;
    }

    @Transactional
    public void cancelOrder(Integer orderId) {

        Order order = orderService.findById(orderId).get();
        order.setStatus(Status.CANCELLED);

        Warehouse warehouse = order.getDepartureWarehouse();
        for(OrderItem orderItem : order.getOrderItems()) {
            Integer bookId = orderItem.getGood().getId();
            Integer amount = orderItem.getQuantity();
            warehouseBookService.addBook(bookId, warehouse.getId(), amount);
        }

    }

    @Transactional
    public void changeOrderStatus(Status status, Integer orderId) {
        if (status == Status.CANCELLED) {
            cancelOrder(orderId);
            return;
        }
        Order order = orderService.findById(orderId).get();
        order.setStatus(status);
    }

    @Transactional
    public void takeAwayOrder(Order order) {
        Bill bill = Bill.builder()
                .order(order)
                .shop(order.getArrivalShop())
                .date(LocalDate.now())
                .build();

        billService.save(bill);

        changeOrderStatus(Status.FINISHED, order.getId());
    }
}
