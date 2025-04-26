package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.order.OrderItem;
import ru.ifellow.jschool.machmetshin.entity.order.OrderStatus;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;
import ru.ifellow.jschool.machmetshin.entity.user.User;
import ru.ifellow.jschool.machmetshin.validator.EntityFoundByIdServiceValidator;
import ru.ifellow.jschool.machmetshin.validator.StorageTypeValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class WebShopManagerService {

    private StorageGoodService storageGoodService;
    private BookService bookService;
    private OrderService orderService;
    private WarehouseService warehouseService;
    private ShopService shopService;
    private BillService billService;
    private UserService userService;

    private StorageTypeValidator storageTypeValidator;
    private EntityFoundByIdServiceValidator entityFoundByIdServiceValidator;

    @Transactional
    public Order createOrder(Integer userId, Integer warehouseId, Integer arrivalShopId, List<OrderItem> orderItems ) {
        Storage arrivalShop = storageTypeValidator.validate(arrivalShopId, StorageType.SHOP);
        int totalPrice = 0;
        LocalDate today = LocalDate.now();

        for( OrderItem orderItem : orderItems ) {
            Integer goodId = orderItem.getGood().getId();
            Integer amount = orderItem.getQuantity();

            if (storageGoodService.getAmountOfGood(goodId, warehouseId) < amount )
                throw new IllegalStateException("not enough goods with id %d in the storage with id %d".formatted(goodId, warehouseId));
        }

        for( OrderItem orderItem : orderItems ) {
            Good good = orderItem.getGood();
            Integer goodId = good.getId();
            Integer amount = orderItem.getQuantity();
            storageGoodService.removeGood(goodId, warehouseId, amount);

            totalPrice += good.getPrice() * amount;
        }
        User user = userService.findById(userId).get();

        Order order = Order.builder()
                .user(user)
                .orderItems(orderItems.stream().collect(Collectors.toSet()) )
                .orderDate(today)
                .arrivalDate(today.plusDays(5))
                .orderStatus(OrderStatus.ASSEMBLING)
                .totalPrice(totalPrice)
                .departureWarehouse(entityFoundByIdServiceValidator.validate(warehouseService, warehouseId, Warehouse.class))
                .arrivalShop(entityFoundByIdServiceValidator.validate(shopService, arrivalShopId, Shop.class))
                .build();

        orderService.save(order);

        return order;
    }

    @Transactional
    public void cancelOrder(Integer orderId) {

        Order order = entityFoundByIdServiceValidator.validate(orderService, orderId ,Order.class);
        order.setOrderStatus(OrderStatus.CANCELLED);

        Warehouse warehouse = order.getDepartureWarehouse();

        for(OrderItem orderItem : order.getOrderItems()) {

            Integer goodId = orderItem.getGood().getId();
            Integer quantity = orderItem.getQuantity();
            storageGoodService.addGood(goodId, warehouse.getId(), quantity);
        }

    }
    @Transactional
    public void changeOrderStatus(OrderStatus orderStatus, Integer orderId) {
        if (orderStatus == OrderStatus.CANCELLED) {
            cancelOrder(orderId);
            return;
        }
        Order order = orderService.findById(orderId).get();
        order.setOrderStatus(orderStatus);
    }

    @Transactional
    public Bill takeAwayOrder(Order order) {
        Bill bill = Bill.builder()
                .order(order)
                .shop(order.getArrivalShop())
                .date(LocalDate.now())
                .build();

        billService.save(bill);
        changeOrderStatus(OrderStatus.FINISHED, order.getId());

        return bill;
    }



}
