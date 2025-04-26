package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
import ru.ifellow.jschool.machmetshin.entity.order.OrderItem;
import ru.ifellow.jschool.machmetshin.entity.order.OrderStatus;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageGood;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageType;
import ru.ifellow.jschool.machmetshin.entity.user.User;
import ru.ifellow.jschool.machmetshin.validator.EntityFoundByIdServiceValidator;
import ru.ifellow.jschool.machmetshin.validator.StorageTypeValidator;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Transactional(readOnly = true)
public class ShopManagerService {

    private StorageGoodService storageGoodService;
    private GoodService goodService;
    private BillService billService;
    private BookService bookService;
    private ShopService shopService;
    private OrderService orderService;
    private UserService userService;
    private StorageTypeValidator storageTypeValidator;
    private EntityFoundByIdServiceValidator entityFoundByIdServiceValidator;


    @Transactional
    public Bill sellGoods(Set<OrderItem> orderItems, Integer shopId, Integer customerId ) {
        Storage storage = storageTypeValidator.validate(shopId, StorageType.SHOP);

        Integer summa = 0;
        for( OrderItem orderItem : orderItems ) {

            int goodId = orderItem.getId();
            int quantity = orderItem.getQuantity();

            int goodPrice = goodService.findById(goodId).get().getPrice();
            storageGoodService.removeGood(goodId, shopId, quantity);
            summa += quantity * goodPrice;
        }
        User user = entityFoundByIdServiceValidator.validate(userService, customerId, User.class);


        Order order = Order.builder()
                .orderItems(orderItems)
                .user(user)
                .orderDate(LocalDate.now())
                .orderStatus(OrderStatus.FINISHED)
                .totalPrice(summa)
                .build();

        orderService.save(order);


        Shop shop = entityFoundByIdServiceValidator.validate(shopService, shopId, Shop.class);

        Bill bill = Bill.builder()
                .order(order)
                .shop(shop)
                .date(LocalDate.now())
                .build();
        billService.save(bill);

        return bill;
    }

    @Transactional
    public void distributeBooks( Integer bookId, List<Integer> shopIds, Integer warehouseId, Integer amount) {
        storageTypeValidator.validate(warehouseId, StorageType.WAREHOUSE);

        for( Integer shopId : shopIds )
            storageTypeValidator.validate(shopId, StorageType.SHOP);

        if (shopIds.isEmpty()) {
            System.out.println("shopIds is empty in distributeBooks()");
            return;
        }

        int booksPerShop = amount / shopIds.size();
        int remainder = amount % shopIds.size();
        Integer firstShopId = 0;


        transportGoodsFromWarehouse(bookId, warehouseId, shopIds.get(firstShopId), remainder + booksPerShop);

        for (int i = 1; i < shopIds.size(); i++)
            transportGoodsFromWarehouse(bookId, warehouseId, shopIds.get(i), booksPerShop);
    }

    @Transactional
    public void transportGoodsFromWarehouse(Integer goodId, Integer warehouseId, Integer shopId, Integer amount)  {
        storageTypeValidator.validate(warehouseId, StorageType.WAREHOUSE);
        storageTypeValidator.validate(shopId, StorageType.SHOP);

        storageGoodService.removeGood(goodId, warehouseId, amount);
        storageGoodService.addGood(goodId, shopId, amount);
    }

    public List<Book> findBooksByGenreAndAuthor(String genre, Author author) {
        return bookService.findBooksByGenreAndAuthor(genre, author);
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

    public Optional<StorageGood> findStorageGoodById(Integer goodId, Integer storageId) {
        return storageGoodService.findByStorageIdAndGoodId(storageId, goodId);
    }


}
