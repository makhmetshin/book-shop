package org.example.service;

import org.example.dao.*;
import org.example.entity.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShopService {

    private int orderIdSequence = 1;

    private ShopBookDao shopBookDao = ShopBookDao.getInstance();
    private WarehouseBookDao warehouseBookDao = WarehouseBookDao.getInstance();
    private BillDao billDao = BillDao.getInstance();
    private BookDao bookDao = BookDao.getInstance();
    private ShopDao shopDao = ShopDao.getInstance();

    private final static ShopService  INSTANCE = new ShopService ();
    private ShopService () {}

    public static ShopService  getInstance() {
        return new ShopService ();
//        return INSTANCE;
    }

    public Set<Shop> findAllShops() {
        return shopDao.findAll();
    }

    public Bill sellBooks(Map<Integer, Integer> items, Integer shopId, String customerFio ) {

        Shop shop = shopDao.findById(shopId).get();
        Integer summa = 0;
        for( Map.Entry<Integer, Integer> entry : items.entrySet() ) {

            int bookId = entry.getKey();
            int amount = entry.getValue();

            int bookPrice = bookDao.findById(bookId).get().getPrice();
            shopBookDao.removeBook(bookId, shopId, amount);
            summa += amount * bookPrice;
        }

        Bill bill = Bill.builder()
                .id(orderIdSequence++)
                .shopId(shopId)
                .items(items)
                .customerFio(customerFio)
                .date(LocalDate.now())
                .summa(summa)
                .build();
        billDao.save(bill);

        return bill;

    }

    public void distributeBooks( Integer bookId, List<Integer> shopIds, Integer warehouseId, Integer amount) {

        int booksPerShop = amount / shopIds.size();
        int remainder = amount % shopIds.size();
        Integer firstShopId = 0;

        transportBooksFromWarehouse(bookId, warehouseId, shopIds.get(firstShopId), remainder);

        for (Integer shopId : shopIds)
            transportBooksFromWarehouse(bookId, warehouseId, shopId, booksPerShop);

    }

    public void transportBooksFromWarehouse(Integer bookId, Integer warehouseId, Integer shopId, Integer amount) {
        warehouseBookDao.removeBook(bookId, warehouseId, amount);
        shopBookDao.addBook(bookId, shopId, amount);
    }

    public Set<Book> findBooksByGenreAndAuthor(String genre, String author) {
        return bookDao.findBooksByGenreAndAuthor(genre, author);
    }

    public Set<Book> findBooksByGenre(String genre) {
        return bookDao.findBooksByGenre(genre);
    }

    public Set<Book> findBooksByAuthor(String author) {
        return bookDao.findBooksByAuthor(author);
    }

    public Set<Book> findBookByAuthorAndTitle(String author, String title) {
        return bookDao.findBooksByAuthorAndTitle(author, title);
    }

    public ShopBook findShopBookByIds(Integer bookId, Integer shopId) {
        return shopBookDao.findByShopIdAndBookId(shopId, bookId).get();
    }

    public WarehouseBook findWarehouseBookByIds(Integer bookId, Integer warehouseId) {
        return warehouseBookDao.findByWarehouseIdAndBookId(warehouseId, bookId).get();
    }

}
