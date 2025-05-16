package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ifellow.jschool.machmetshin.dto.order.CreateWebOrderDto;
import ru.ifellow.jschool.machmetshin.dto.order.OrderItemDtoWithGoodId;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageGoodDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageGoodGetAmountDto;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.order.OrderItem;
import ru.ifellow.jschool.machmetshin.entity.order.OrderStatus;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageGood;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;
import ru.ifellow.jschool.machmetshin.entity.user.User;
import ru.ifellow.jschool.machmetshin.service.manager.WebShopManagerService;
import ru.ifellow.jschool.machmetshin.validator.EntityExistsValidator;


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
    private UserService userService;
    @Mock
    private EntityExistsValidator entityExistsValidator;
    @Mock
    private BillService billService;
    @Mock
    private WarehouseService warehouseService;
    @Mock
    private ShopService shopService;
    @Mock
    private GoodService goodService;

    @Spy
    @InjectMocks
    private WebShopManagerService spyWebShopManagerService;

    @Test
    public void createOrderTest() {

        List<OrderItemDtoWithGoodId> orderItemDtoWithGoodIds = new ArrayList<>();
        orderItemDtoWithGoodIds.add(new OrderItemDtoWithGoodId(1, 10, 100));
        orderItemDtoWithGoodIds.add(new OrderItemDtoWithGoodId(2, 20, 200));

        Mockito.doReturn(0).when(storageGoodService)
                .getAmountOfGood(Mockito.any(StorageGoodGetAmountDto.class));
        assertThrows(IllegalStateException.class, () -> {
            spyWebShopManagerService.createOrder(
                    new CreateWebOrderDto(1,1, orderItemDtoWithGoodIds,11));
        });


        Mockito.doReturn(Optional.of(new User())).when(userService).findById(Mockito.anyInt());
        Mockito.doReturn(100).when(storageGoodService)
                .getAmountOfGood(Mockito.any(StorageGoodGetAmountDto.class));

        Mockito.doReturn(new Shop()).when(entityExistsValidator)
                .validate(Mockito.any(), Mockito.anyInt(), Mockito.eq(Shop.class));

        Mockito.doReturn(new Warehouse()).when(entityExistsValidator)
                .validate(Mockito.any(), Mockito.anyInt(), Mockito.eq(Warehouse.class));

        Mockito.doReturn(new Book()).when(entityExistsValidator)
                .validate(Mockito.any(), Mockito.anyInt(), Mockito.eq(Good.class));

        Assertions.assertThat(spyWebShopManagerService.createOrder(
                new CreateWebOrderDto(1,1, orderItemDtoWithGoodIds,1))
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

        Mockito.doReturn(order).when(entityExistsValidator)
                .validate(Mockito.any(),
                        Mockito.eq(1),
                        Mockito.eq(Order.class));

        spyWebShopManagerService.cancelOrder(1);

        Mockito.verify(storageGoodService).addGood(Mockito.argThat(dto ->
                dto.getGoodId() == 0 && dto.getQuantity() == 0
        ));

        Mockito.verify(storageGoodService).addGood(Mockito.argThat(dto ->
                dto.getGoodId() == 1 && dto.getQuantity() == 10
        ));
    }

    @Test
    public void changeOrderStatusTest() {
        Order order = Order.builder().id(1).build();
        Mockito.doReturn(Optional.of(order)).when(orderService).findById(1);
        spyWebShopManagerService.changeOrderStatus(OrderStatus.TRANSIT, 1);
        Assertions.assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.TRANSIT);
    }

    @Test
    public void takeawayOrderTest() {
        Shop shop = new Shop();

        Order order = Order.builder().id(1).orderStatus(OrderStatus.READY).arrivalShop(shop).build();
        Mockito.doReturn(order).when(entityExistsValidator).validate(Mockito.any(),
                Mockito.anyInt(), Mockito.eq(Order.class));

        Mockito.doNothing().when(spyWebShopManagerService)
                .changeOrderStatus(Mockito.any(OrderStatus.class), Mockito.anyInt());

        spyWebShopManagerService.takeawayOrder(1);
        Mockito.verify(spyWebShopManagerService).changeOrderStatus(OrderStatus.FINISHED, 1);

        Assertions.assertThat(spyWebShopManagerService.takeawayOrder(1).getOrder().getId())
                .isEqualTo(1);
    }

    @Test
    public void findBooksByGenreTest() {
        Book book = new Book();
        book.setGenre("genre");
        List<Book> books = new ArrayList<>();
        books.add(book);

        Mockito.doReturn(books).when(bookService)
                .findBooksByGenre("genre");

        Assertions.assertThat(spyWebShopManagerService.findBooksByGenre("genre").getFirst().getGenre())
                .isEqualTo("genre");
    }

    @Test
    public void findBooksByAuthorTest() {
        Book book = new Book();
        book.setGenre("genre");
        Author author = new Author();
        List<Book> books = new ArrayList<>();
        books.add(book);

        Mockito.doReturn(books).when(bookService)
                .findBooksByAuthor(author);

        Assertions.assertThat(spyWebShopManagerService.findBooksByAuthor(author).getFirst().getGenre())
                .isEqualTo("genre");
    }

    @Test
    public void findBooksByAuthorAndTitleTest() {
        Book book = new Book();
        book.setGenre("genre");
        Author author = new Author();
        List<Book> books = new ArrayList<>();
        books.add(book);

        Mockito.doReturn(books).when(bookService)
                .findBooksByAuthorAndTitle(author, "title");

        Assertions.assertThat(
                        spyWebShopManagerService.findBooksByAuthorAndTitle(author, "title").getFirst().getGenre())
                .isEqualTo("genre");
    }

    @Test
    public void findStorageGoodByIdTest() {
        StorageGood storageGood = new StorageGood();
        storageGood.setId(1);

        Mockito.doReturn(Optional.of(storageGood)).when(storageGoodService)
                .findByStorageIdAndGoodId(1,1);

        Assertions.assertThat(
                        spyWebShopManagerService.findStorageGoodById(1,1).get().getId())
                .isEqualTo(1);
    }

}
