package ru.ifellow.jschool.machmetshin.service.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.dto.good.GoodDto;
import ru.ifellow.jschool.machmetshin.dto.good.book.AuthorDto;
import ru.ifellow.jschool.machmetshin.dto.good.book.BookDto;
import ru.ifellow.jschool.machmetshin.dto.good.book.PublisherDto;
import ru.ifellow.jschool.machmetshin.dto.order.CreateWebOrderDto;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.FindBooksDto;
import ru.ifellow.jschool.machmetshin.entity.good.GoodType;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.order.OrderItem;
import ru.ifellow.jschool.machmetshin.entity.order.OrderStatus;
import ru.ifellow.jschool.machmetshin.entity.storage.*;
import ru.ifellow.jschool.machmetshin.service.*;
import ru.ifellow.jschool.machmetshin.validator.EntityExistsValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class WebShopManagerService extends AbstractShopManager{

    private final StorageGoodService storageGoodService;
    private final OrderService orderService;
    private final BookService bookService;
    private final BillService billService;
    private final EntityExistsValidator entityExistsValidator;
    private final ShopService shopService;
    private final UserService userService;
    private final WarehouseService warehouseService;
    private final AuthorService authorService;

    @Autowired
    public WebShopManagerService(StorageGoodService storageGoodService, OrderService orderService,
                                 WarehouseService warehouseService, ShopService shopService, UserService userService,
                                 GoodService goodService, EntityExistsValidator entityExistsValidator,
                                 BillService billService, BookService bookService, AuthorService authorService) {
        super(storageGoodService, orderService, warehouseService, shopService, userService, goodService, entityExistsValidator, billService);
        this.storageGoodService = storageGoodService;
        this.billService = billService;
        this.bookService = bookService;
        this.shopService = shopService;
        this.orderService = orderService;
        this.userService = userService;
        this.entityExistsValidator = entityExistsValidator;
        this.warehouseService = warehouseService;
        this.authorService = authorService;
    }

    @Transactional
    public Order createOrder( CreateWebOrderDto createWebOrderDto) {
        return super.createOrder(createWebOrderDto, true);
    }

    @Transactional
    public void cancelOrder(Integer orderId) {

        Order order = entityExistsValidator.validate(orderService.findById(orderId), orderId, Order.class);
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
    public Bill takeawayOrder(Integer orderId) {
        Order order = entityExistsValidator.validate(orderService.findById(orderId), orderId, Order.class);
        Bill bill = Bill.builder()
                .order(order)
                .shop(order.getArrivalShop())
                .date(LocalDate.now())
                .returned(false)
                .build();

        billService.save(bill);
        changeOrderStatus(OrderStatus.FINISHED, order.getId());

        return bill;
    }


    public List<BookDto> findBooksByGenreAndAuthor(FindBooksDto findBooksDto) {
        String genre;
        Integer authorId;
        if(findBooksDto.getGenre() != null && findBooksDto.getAuthorId() != null) {
            genre = findBooksDto.getGenre();
            authorId = findBooksDto.getAuthorId();
        }
        else throw new IllegalArgumentException("genre or authorId is null or both of them");


        Author author = entityExistsValidator
                .validate(authorService.findById(authorId), authorId, Author.class);

        return bookService.findBooksByGenreAndAuthor(genre, author).stream()
                .map(book -> BookDto.builder()
                        .id(book.getId())
                        .goodType(book.getGoodType())
                        .price(book.getPrice())
                        .description(book.getDescription())
                        .ISBN(book.getISBN())
                        .title(book.getTitle())
                        .pagesAmount(book.getPagesAmount())
                        .authorDto(new AuthorDto(book.getAuthor().getId(), book.getAuthor().getFirstName(),
                                book.getAuthor().getLastName(), book.getAuthor().getBirthDate() )
                        )
                        .publisherDto(new PublisherDto(book.getPublisher().getId(), book.getPublisher().getName(),
                                book.getPublisher().getAddress(), book.getPublisher().getCity())
                        )
                        .genre(book.getGenre())
                        .publishedDate(book.getPublishedDate())
                        .build())
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByGenre(String genre) {
        return bookService.findBooksByGenre(genre);
    }

    public List<Book> findBooksByAuthor(Author author) {
        return bookService.findBooksByAuthor(author);
    }

    public List<Book> findBooksByAuthorAndTitle(Author author, String title) {
        return bookService.findBooksByAuthorAndTitle(author, title);
    }


}
