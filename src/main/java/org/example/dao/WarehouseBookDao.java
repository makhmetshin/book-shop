package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class WarehouseBookDao extends StorageBookDao{

    private DataSource dataSource;
    private Set<StorageBook> warehouseBooks;

    @Autowired
    public WarehouseBookDao(DataSource dataSource) {
        super(dataSource.getWarehouseBooks());
        this.dataSource = dataSource;
        this.warehouseBooks = dataSource.getWarehouseBooks();
    }

    public static WarehouseBookDao getInstance() {
        return null;
//            return INSTANCE;
    }

    public WarehouseBookDao() {}

    public Set<StorageBook> findAll() {
        return super.findAll();
    }

    public Optional<? extends StorageBook> findByWarehouseIdAndBookId(Integer warehouseId, Integer bookId) {
        return super.findByStorageIdAndBookId(warehouseId, bookId);
    }

    public void addBook(Integer bookId, Integer warehouseId, int amount) {
        boolean exists = super.addExistingBook(bookId, warehouseId, amount);

        if(!exists) {
            WarehouseBook newBook = WarehouseBook.builder()
                    .storageId(warehouseId)
                    .bookId(bookId)
                    .bookAmount(amount)
                    .build();
            warehouseBooks.add(newBook);
        }
    }

    public void removeBook(Integer bookId, Integer warehouseId, int amount) {
        super.removeBook(bookId, warehouseId, amount);
    }

    public void addBooks(List<Book> books, Integer warehouseId) {
        Map<Integer, Integer> bookCountMap = new HashMap<>();

        for (Book book : books)
            bookCountMap.put(book.getId(), bookCountMap.getOrDefault(book.getId(), 0) + 1);

        for (Map.Entry<Integer, Integer> entry : bookCountMap.entrySet()) {
            Integer bookId = entry.getKey();
            Integer amount = entry.getValue();
            addBook(bookId, warehouseId, amount);
        }
    }

    public Integer getAmountOfBook(Integer bookId, Integer warehouseId) {
        return super.getAmountOfBook(bookId, warehouseId);
    }






}
