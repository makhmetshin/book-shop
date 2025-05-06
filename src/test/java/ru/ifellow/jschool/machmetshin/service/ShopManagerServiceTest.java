package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ifellow.jschool.machmetshin.dto.order.CreateOrderDto;
import ru.ifellow.jschool.machmetshin.dto.order.OrderItemDtoWithGoodId;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.DistributeGoodDto;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.FindBooksDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageGoodDto;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.good.book.Publisher;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.storage.*;
import ru.ifellow.jschool.machmetshin.entity.user.User;
import ru.ifellow.jschool.machmetshin.service.manager.ShopManagerService;
import ru.ifellow.jschool.machmetshin.validator.EntityExistsValidator;
import ru.ifellow.jschool.machmetshin.validator.StorageTypeValidator;

import java.util.*;

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
    @Mock
    private WarehouseService warehouseService;

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

        Mockito.doReturn(user).when(entityExistsValidator).validate(
                        Mockito.any(Optional.class),
                        Mockito.any(Integer.class),
                        Mockito.eq(User.class));

        Mockito.doReturn(good).when(entityExistsValidator).validate(
                Mockito.any(Optional.class),
                Mockito.any(Integer.class),
                Mockito.eq(Good.class));

        Mockito.doNothing().when(orderService).save(Mockito.any(Order.class));

        Mockito.doReturn(shop).when(entityExistsValidator).validate(
                Mockito.any(),
                Mockito.any(Integer.class),
                Mockito.eq(Shop.class));

        Mockito.doNothing().when(billService).save(Mockito.any(Bill.class));

        Mockito.doReturn(100).when(storageGoodService).getAmountOfGood(
                Mockito.eq(1), Mockito.eq(15), Mockito.any());
        Mockito.doReturn(100).when(storageGoodService).getAmountOfGood(
                Mockito.eq(2), Mockito.eq(15), Mockito.any());

        List<OrderItemDtoWithGoodId> orderItemDtoWithGoodIds = new ArrayList<>();
        orderItemDtoWithGoodIds.add(new OrderItemDtoWithGoodId(1, 10, 100));
        orderItemDtoWithGoodIds.add(new OrderItemDtoWithGoodId(2, 20, 200));
        CreateOrderDto createOrderDto = new CreateOrderDto(1, 15, orderItemDtoWithGoodIds);

        Assertions.assertThat(shopManagerService.sellGoods(createOrderDto)
                        .getShop().getId()).isEqualTo(15);

    }
    @Test
    public void distributeGoodsTest() {
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();

        List<Integer> shopIds = new ArrayList<>();
        shopIds.add(1);
        shopIds.add(2);

        DistributeGoodDto distributeGoodDto = new DistributeGoodDto(1, shopIds, 1, 13);

        Assertions.assertThatCode(() -> spyShopManagerService.distributeGood(distributeGoodDto))
                .doesNotThrowAnyException();

        Mockito.verify(storageGoodService)
                .removeGood(1, 1, 7);
        Mockito.verify(storageGoodService)
                .addGood(1, 2, 6);
    }


    @Test
    public void findBooksInShopByGenreAndAuthorTest() {
        Book book = new Book();
        StorageGoodDto storageGoodDto = new StorageGoodDto(1, 1, 10);
        book.setGenre("genre");
        Author author = Author.builder().id(1).build();
        Publisher publisher = Publisher.builder().id(1).build();
        book.setAuthor(author);
        book.setPublisher(publisher);

        List<Book> books = new ArrayList<>();
        List<StorageGoodDto> storageGoodDtos = new ArrayList<>();
        books.add(book);
        storageGoodDtos.add(storageGoodDto);

        Mockito.doReturn(book).when(entityExistsValidator).validate(
                Mockito.any(), Mockito.any(Integer.class), Mockito.eq(Book.class));
        Mockito.doReturn(storageGoodDtos).when(storageGoodService).findGoodIdsByStorageId(1);

        FindBooksDto findBooksDto = new FindBooksDto("genre", null, 1);

        Assertions.assertThat(
                shopManagerService.findBooksInShopByGenreAndAuthor(1, findBooksDto).getFirst().getGenre())
                .isEqualTo("genre");
    }



}
