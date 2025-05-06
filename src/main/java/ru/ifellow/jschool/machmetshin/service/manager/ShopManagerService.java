package ru.ifellow.jschool.machmetshin.service.manager;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.dto.good.book.AuthorDto;
import ru.ifellow.jschool.machmetshin.dto.good.book.BookDto;
import ru.ifellow.jschool.machmetshin.dto.good.book.PublisherDto;
import ru.ifellow.jschool.machmetshin.dto.order.CreateOrderDto;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.DistributeGoodDto;
import ru.ifellow.jschool.machmetshin.dto.servicesDto.FindBooksDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageDto;
import ru.ifellow.jschool.machmetshin.dto.storage.StorageGoodDto;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.storage.*;
import ru.ifellow.jschool.machmetshin.service.*;
import ru.ifellow.jschool.machmetshin.validator.EntityExistsValidator;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ShopManagerService extends AbstractShopManager{

    private final StorageGoodService storageGoodService;
    private final BillService billService;
    private final BookService bookService;
    private final ShopService shopService;
    private final OrderService orderService;
    private final UserService userService;
    private final EntityExistsValidator entityExistsValidator;
    private final WarehouseService warehouseService;

    @Autowired
    public ShopManagerService(StorageGoodService storageGoodService, OrderService orderService,
                              WarehouseService warehouseService, ShopService shopService, UserService userService,
                              GoodService goodService, EntityExistsValidator entityExistsValidator,
                              BillService billService, BookService bookService) {
        super(storageGoodService, orderService, warehouseService, shopService, userService, goodService, entityExistsValidator, billService);
        this.storageGoodService = storageGoodService;
        this.billService = billService;
        this.bookService = bookService;
        this.shopService = shopService;
        this.orderService = orderService;
        this.userService = userService;
        this.entityExistsValidator = entityExistsValidator;
        this.warehouseService = warehouseService;
    }

    @Transactional
    public Bill sellGoods(CreateOrderDto createOrderDto ) {
        Order order = super.createOrder(createOrderDto, false);
        Integer shopId = createOrderDto.getReceiveStorageId();
        Shop shop = entityExistsValidator.validate(shopService.findById(shopId), shopId, Shop.class);

        Bill bill = Bill.builder()
                .order(order)
                .shop(shop)
                .returned(false)
                .date(LocalDate.now())
                .build();
        billService.save(bill);

        return bill;
    }

    @Transactional
    public void distributeGood(DistributeGoodDto distributeGoodDto) {

        Integer goodId = distributeGoodDto.getGoodId();
        List<Integer> shopIds = distributeGoodDto.getShopIds();
        Integer warehouseId = distributeGoodDto.getWarehouseId();
        Integer amount = distributeGoodDto.getAmount();

        entityExistsValidator.validate(warehouseService.findById(warehouseId), warehouseId, Warehouse.class);

        for( Integer shopId : shopIds )
            entityExistsValidator.validate(shopService.findById(shopId), shopId, Shop.class);

        if (shopIds.isEmpty()) {
            System.out.println("shopIds is empty in distributeGoods()");
            return;
        }

        int booksPerShop = amount / shopIds.size();
        int remainder = amount % shopIds.size();

        Iterator<Integer> iterator = shopIds.iterator();

        Integer firstShopId = iterator.next();
        transportGoodsFromWarehouse(goodId, warehouseId, firstShopId, booksPerShop + remainder);

        while (iterator.hasNext()) {
            Integer shopId = iterator.next();
            transportGoodsFromWarehouse(goodId, warehouseId, shopId, booksPerShop);
        }
    }

    private void transportGoodsFromWarehouse(Integer goodId, Integer warehouseId, Integer shopId, Integer amount)  {
        entityExistsValidator.validate(warehouseService.findById(warehouseId), warehouseId, Warehouse.class);
        entityExistsValidator.validate(shopService.findById(shopId), shopId, Shop.class);

        storageGoodService.removeGood(goodId, warehouseId, amount);
        storageGoodService.addGood(goodId, shopId, amount);
    }

    public List<BookDto> findBooksInShopByGenreAndAuthor(Integer shopId, FindBooksDto findBooksDto) {
        List<StorageGoodDto> storageGoodDtos = storageGoodService.findGoodIdsByStorageId(shopId);

        List<Book> books = new ArrayList<>();
        for (StorageGoodDto storageGoodDto : storageGoodDtos) {
            Integer bookId = storageGoodDto.getGoodId();
            books.add(entityExistsValidator.validate(bookService.findById(bookId), bookId, Book.class));
        }

        Integer authorId = findBooksDto.getAuthorId();
        String genre = findBooksDto.getGenre();

        return books.stream()
                .filter(book -> book.getAuthor().getId().equals(authorId))
                .filter(book -> book.getGenre().equals(genre))
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





}
