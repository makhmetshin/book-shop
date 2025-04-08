package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Book;
import org.example.entity.ShopBook;
import org.example.entity.StorageBook;
import org.example.entity.WarehouseBook;
import org.example.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ShopBookDao extends StorageBookDao {

    private DataSource dataSource;
    private Set<StorageBook> shopBooks;

    @Autowired
    public ShopBookDao(DataSource dataSource) {
        super(dataSource.getShopBooks());
        this.dataSource = dataSource;
        this.shopBooks = dataSource.getShopBooks();
    }

    public static ShopBookDao getInstance() {
        return null;
    }

    public ShopBookDao() {}

    public Set<StorageBook> findAll() {
        return super.findAll();
    }

    public Optional<? extends StorageBook> findByShopIdAndBookId(Integer shopId, Integer bookId) {
        return super.findByStorageIdAndBookId(shopId, bookId);
    }

    public void addBook(Integer bookId, Integer shopId, int amount) {
        boolean exists = super.addExistingBook(bookId, shopId, amount);

        if(!exists) {
            WarehouseBook newBook = WarehouseBook.builder()
                    .storageId(shopId)
                    .bookId(bookId)
                    .bookAmount(amount)
                    .build();
            shopBooks.add(newBook);
        }
    }

    public void removeBook(Integer bookId, Integer shopId, int amount) {
        super.removeBook(bookId, shopId, amount);
    }

    public void addBooks(List<Book> books, Integer shopId) {
        Map<Integer, Integer> bookCountMap = new HashMap<>();

        for (Book book : books)
            bookCountMap.put(book.getId(), bookCountMap.getOrDefault(book.getId(), 0) + 1);

        for (Map.Entry<Integer, Integer> entry : bookCountMap.entrySet()) {
            Integer bookId = entry.getKey();
            Integer amount = entry.getValue();
            addBook(bookId, shopId, amount);
        }
    }

    public Integer getAmountOfBook(Integer bookId, Integer shopId) {
        return super.getAmountOfBook(bookId, shopId);
    }

}
