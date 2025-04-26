package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.order.OrderItem;
import ru.ifellow.jschool.machmetshin.entity.order.OrderStatus;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;
import ru.ifellow.jschool.machmetshin.entity.user.User;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;
import ru.ifellow.jschool.machmetshin.validator.EntityFoundByIdServiceValidator;
import ru.ifellow.jschool.machmetshin.validator.StorageTypeValidator;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class WebShopManagerServiceTest {
    @Mock
    private StorageGoodService storageGoodService;
    @Mock
    private BookService bookService;
    @Mock
    private OrderService orderService;
    @Mock
    private WarehouseService warehouseService;
    @Mock
    private ShopService shopService;
    @Mock
    private BillService billService;
    @Mock
    private UserService userService;
    @Mock
    private StorageTypeValidator storageTypeValidator;
    @Mock
    private EntityFoundByIdServiceValidator entityFoundByIdServiceValidator;

    @Spy
    @InjectMocks
    private WebShopManagerService spyWebShopManagerService;

    @Test
    public void createOrderTest() {

        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Good good = Book.builder().build();
            good.setId(i);
            good.setPrice(i * 100);

            OrderItem orderItem =
                    OrderItem.builder()
                    .id(i)
                    .good(good)
                    .priceAtPurchase(i * 100)
                    .quantity(i * 10)
                    .build();

            orderItems.add(orderItem);
        }

        Mockito.doReturn(0).when(storageGoodService).getAmountOfGood(Mockito.anyInt(), Mockito.anyInt());
        assertThrows(IllegalStateException.class, () -> {
            spyWebShopManagerService.createOrder(1, 1, 1, orderItems);
        });

        Mockito.doReturn(Optional.of(new User())).when(userService).findById(Mockito.anyInt());
        Mockito.doReturn(100).when(storageGoodService).getAmountOfGood(Mockito.anyInt(), Mockito.anyInt());
        Assertions.assertThat(spyWebShopManagerService.createOrder(1, 1, 1, orderItems)
                .getOrderItems()).hasSize(2);
    }

    @Test
    public void cancelOrderTest() {

        Set<OrderItem> orderItems = new HashSet<>();
        for (int i = 0; i < 2; i++) {
            Good good = Book.builder().build();
            good.setId(i);
            good.setPrice(i * 100);

            OrderItem orderItem =
                    OrderItem.builder()
                            .id(i)
                            .good(good)
                            .priceAtPurchase(i * 100)
                            .quantity(i * 10)
                            .build();

            orderItems.add(orderItem);
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setId(1);

        Order order = Order.builder()
                .id(1)
                .orderItems(orderItems)
                .departureWarehouse(warehouse)
                .build();

        Mockito.doReturn(order).when(entityFoundByIdServiceValidator)
                .validate(Mockito.any(Findable.class),
                        Mockito.eq(1),
                        Mockito.eq(Order.class));

        spyWebShopManagerService.cancelOrder(1);

        Mockito.verify(storageGoodService).addGood(
                Mockito.eq(0), Mockito.anyInt(), Mockito.eq(0));
        Mockito.verify(storageGoodService).addGood(
                Mockito.eq(1), Mockito.anyInt(), Mockito.eq(10));
    }

    @Test
    public void changeOrderStatusTest() {
        Order order = Order.builder().id(1).build();
        Mockito.doReturn(Optional.of(order)).when(orderService).findById(1);
        spyWebShopManagerService.changeOrderStatus(OrderStatus.TRANSIT, 1);
        Assertions.assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.TRANSIT);
    }

    @Test
    public void takeAwayOrderTest() {
        Shop shop = new Shop();

        Order order = Order.builder().id(1).arrivalShop(shop).build();
        Mockito.doNothing().when(spyWebShopManagerService)
                .changeOrderStatus(Mockito.any(OrderStatus.class), Mockito.anyInt());

        spyWebShopManagerService.takeAwayOrder(order);
        Mockito.verify(spyWebShopManagerService).changeOrderStatus(OrderStatus.FINISHED, 1);

        Assertions.assertThat(spyWebShopManagerService.takeAwayOrder(order).getOrder().getId())
                .isEqualTo(1);
    }

}
