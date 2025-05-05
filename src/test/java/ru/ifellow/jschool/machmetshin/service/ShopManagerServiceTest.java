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
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.order.OrderItem;
import ru.ifellow.jschool.machmetshin.entity.storage.*;
import ru.ifellow.jschool.machmetshin.entity.user.User;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;
import ru.ifellow.jschool.machmetshin.service.manager.ShopManagerService;
import ru.ifellow.jschool.machmetshin.validator.EntityExistsValidator;
import ru.ifellow.jschool.machmetshin.validator.StorageTypeValidator;

import java.util.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ShopManagerServiceTest {

    @Mock
    private StorageGoodService storageGoodService;
    @Mock
    private GoodService goodService;
    @Mock
    private BillService billService;
    @Mock
    private BookService bookService;
    @Mock
    private ShopService shopService;
    @Mock
    private OrderService orderService;
    @Mock
    private UserService userService;
    @Mock
    private StorageTypeValidator storageTypeValidator;
    @Mock
    private EntityExistsValidator entityExistsValidator;

    @Spy
    @InjectMocks
    private ShopManagerService spyShopManagerService;
    @InjectMocks
    private ShopManagerService shopManagerService;

    @Test
    public void sellGoodsTest() {
        Shop shop = new Shop();
        shop.setId(15);
        Good good = new Book();
        User user = new User();
        Order order = new Order();
        Bill bill = new Bill();
        good.setPrice(100);

        Mockito.doReturn(shop).when(storageTypeValidator).validate(1, StorageType.SHOP);

        Mockito.doReturn(user).when(entityExistsValidator).validate(
                        Mockito.any(Findable.class),
                        Mockito.any(Integer.class),
                        Mockito.eq(User.class));

        Mockito.doNothing().when(orderService).save(Mockito.any(Order.class));

        Mockito.doReturn(shop).when(entityExistsValidator).validate(
                Mockito.any(shopService.getClass()),
                Mockito.any(Integer.class),
                Mockito.eq(Shop.class));

        Mockito.doNothing().when(billService).save(Mockito.any(Bill.class));

        Set<OrderItem> orderItems = new HashSet<>();

        Assertions.assertThat(shopManagerService.sellGoods(orderItems, 1, 1)
                        .getShop().getId()).isEqualTo(15);

    }
    @Test
    public void distributeGoodsTest() {
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();

        Mockito.doReturn(shop).when(storageTypeValidator)
                .validate(Mockito.any(Integer.class), Mockito.eq(StorageType.SHOP));

        Mockito.doReturn(warehouse).when(storageTypeValidator)
                .validate(Mockito.any(Integer.class), Mockito.eq(StorageType.WAREHOUSE));

        Mockito.doNothing().when(spyShopManagerService)
                .transportGoodsFromWarehouse(
                        Mockito.anyInt(), Mockito.anyInt(),
                        Mockito.anyInt(), Mockito.anyInt());

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        spyShopManagerService.distributeGood(1, ids, 1, 13);

        Mockito.verify(spyShopManagerService)
                .transportGoodsFromWarehouse(1, 1, 1, 7);
        Mockito.verify(spyShopManagerService)
                .transportGoodsFromWarehouse(1, 1, 2, 6);
    }

    @Test
    public void transportGoodsFromWarehouseTest() {

        spyShopManagerService.transportGoodsFromWarehouse(1,2,1, 100);

        Mockito.verify(storageTypeValidator)
                .validate(2,StorageType.WAREHOUSE);
        Mockito.verify(storageTypeValidator)
                .validate(1,StorageType.SHOP);

        Mockito.verify(storageGoodService)
                .removeGood(1, 2, 100);
        Mockito.verify(storageGoodService)
                .addGood(1, 1, 100);
    }

    @Test
    public void findBooksByGenreAndAuthorTest() {
        Book book = new Book();
        book.setGenre("genre");
        Author author = new Author();
        List<Book> books = new ArrayList<>();
        books.add(book);

        Mockito.doReturn(books).when(bookService)
                .findBooksByGenreAndAuthor("genre", author);

        Assertions.assertThat(
                shopManagerService.findBooksByGenreAndAuthor("genre", author).getFirst().getGenre())
                .isEqualTo("genre");
    }

    @Test
    public void findBooksByGenreTest() {
        Book book = new Book();
        book.setGenre("genre");
        List<Book> books = new ArrayList<>();
        books.add(book);

        Mockito.doReturn(books).when(bookService)
                .findBooksByGenre("genre");

        Assertions.assertThat(shopManagerService.findBooksByGenre("genre").getFirst().getGenre())
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

        Assertions.assertThat(shopManagerService.findBooksByAuthor(author).getFirst().getGenre())
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
                shopManagerService.findBooksByAuthorAndTitle(author, "title").getFirst().getGenre())
                .isEqualTo("genre");
    }

    @Test
    public void findStorageGoodByIdTest() {
        StorageGood storageGood = new StorageGood();
        storageGood.setId(1);

        Mockito.doReturn(Optional.of(storageGood)).when(storageGoodService)
                .findByStorageIdAndGoodId(1,1);

        Assertions.assertThat(
                        shopManagerService.findStorageGoodById(1,1).get().getId())
                .isEqualTo(1);
    }


}
