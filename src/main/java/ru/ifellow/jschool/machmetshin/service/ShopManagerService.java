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
import ru.ifellow.jschool.machmetshin.entity.order.Status;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.ShopBook;
import ru.ifellow.jschool.machmetshin.entity.storage.WarehouseBook;
import ru.ifellow.jschool.machmetshin.entity.user.User;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Transactional(readOnly = true)
public class ShopManagerService {

    private ShopBookService shopBookService;
    private WarehouseBookService warehouseBookService;
    private BillService billService;
    private BookService bookService;
    private ShopService shopService;
    private OrderService orderService;
    private UserService userService;

    public List<Shop> findAllShops() {
        return shopService.findAll();
    }

    @Transactional
    public Bill sellBooks(Set<OrderItem> orderItems, Integer shopId, Integer customerId ) {
        //в методе нет обработки кейсов, когда не нашлось магазина/книги/пользователя по айди. Можно хотя бы orElseThrow везде напихать

        Shop shop = shopService.findById(shopId).get();

        //тут кажется тоже можно через StreamAPI, посмотри в сторону метода reduce
        Integer summa = 0;
        for( OrderItem orderItem : orderItems ) {

            int bookId = orderItem.getId(); //Тут ошибочка? Айди позиции заказа - это не айди книги. И тут как раз явно видно преимущество UUID-ных айди перед числовыми айди)
            int amount = orderItem.getQuantity();

            int bookPrice = bookService.findById(bookId).get().getPrice();
            shopBookService.removeBook(bookId, shopId, amount);
            summa += amount * bookPrice;
        }
        User user = userService.findById(customerId).get();

        Order order = Order.builder()
                .orderItems(orderItems)
                .user(user)
                .orderDate(LocalDate.now())
                .status(Status.FINISHED)
                .totalPrice(summa)
                .build();

        orderService.save(order);

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

        if (shopIds.isEmpty()) {
            System.out.println("shopIds is empty in distributeBooks()");
            return;
        }

        int booksPerShop = amount / shopIds.size();
        int remainder = amount % shopIds.size();
        Integer firstShopId = 0; //а точно такой есть? Я вот внезапно засомневалась, что в БД автогенерируемые айди с 0 начинаются


        transportBooksFromWarehouse(bookId, warehouseId, shopIds.get(firstShopId), remainder);

        for (Integer shopId : shopIds)
            transportBooksFromWarehouse(bookId, warehouseId, shopId, booksPerShop);

    }

    //метод точно должен быть публичным?
    // Transactional в текущем варианте не сработает, так как вызов внутри класса без проксирования
    @Transactional
    public void transportBooksFromWarehouse(Integer bookId, Integer warehouseId, Integer shopId, Integer amount) {
        warehouseBookService.removeBook(bookId, warehouseId, amount);
        shopBookService.addBook(bookId, shopId, amount);
    }

    public Set<Book> findBooksByGenreAndAuthor(String genre, Author author) {
        return bookService.findBooksByGenreAndAuthor(genre, author);
    }

    public Set<Book> findBooksByGenre(String genre) {
        return bookService.findBooksByGenre(genre);
    }

    public Set<Book> findBooksByAuthor(Author author) {
        return bookService.findBooksByAuthor(author);
    }

    public Set<Book> findBooksByAuthorAndTitle(Author author, String title) {
        return bookService.findBooksByAuthorAndTitle(author, title);
    }

    public Optional<ShopBook> findShopBookByIds(Integer bookId, Integer shopId) {
        return shopBookService.findByShopIdAndBookId(shopId, bookId);
    }

    public Optional<WarehouseBook> findWarehouseBookByIds(Integer bookId, Integer warehouseId) {
        return warehouseBookService.findByWarehouseIdAndBookId(warehouseId, bookId);
    }

}
