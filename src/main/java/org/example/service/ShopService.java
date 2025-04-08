package org.example.service;

import org.example.dao.*;
import org.example.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class ShopService {

    private int orderIdSequence = 1;

    private ShopBookDao shopBookDao;
    private WarehouseBookDao warehouseBookDao;

    private BillDao billDao;
    private BookDao bookDao;
    private ShopDao shopDao;

    @Autowired
    public ShopService(ShopBookDao shopBookDao, WarehouseBookDao warehouseBookDao,
                       BillDao billDao, BookDao bookDao, ShopDao shopDao) {
        this.shopBookDao = shopBookDao;
        this.warehouseBookDao = warehouseBookDao;
        this.billDao = billDao;
        this.bookDao = bookDao;
        this.shopDao = shopDao;
    }

    public ShopService () {}

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

        if (shopIds.isEmpty()) {
            System.out.println("shopIds is empty in distributeBooks()");
            return;
        }

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

    public Optional<? extends StorageBook> findShopBookByIds(Integer bookId, Integer shopId) {
        return shopBookDao.findByShopIdAndBookId(shopId, bookId);
    }

    public Optional<? extends StorageBook> findWarehouseBookByIds(Integer bookId, Integer warehouseId) {
        return warehouseBookDao.findByWarehouseIdAndBookId(warehouseId, bookId);
    }

}
