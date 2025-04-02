package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Book;
import org.example.entity.StorageBook;
import org.example.entity.WarehouseBook;

import java.util.*;

public class StorageBookDao {
    Set<StorageBook> storageBooks;

    public StorageBookDao(Set<StorageBook> storageBooks) {
        this.storageBooks = storageBooks;
    }

    private final static StorageBookDao INSTANCE = new StorageBookDao();

    private StorageBookDao () {}

//    public static ShopBookDao getInstance() {
//        return new ShopBookDao();
////        return INSTANCE;
//    }
    private DataSource dataSource = DataSource.getInstance();


    public Set<StorageBook> findAll() {
        return dataSource.getWarehouseBooks();

    }

    public Optional<StorageBook> findByStorageIdAndBookId(Integer warehouseId, Integer bookId) {

        for (StorageBook sb : storageBooks)
            if (sb.getStorageId().equals(warehouseId) && sb.getBookId().equals(bookId))
                return Optional.of(sb);

        return Optional.empty();
    }

    public void addBook(Integer bookId, Integer warehouseId, int amount) {


        for (StorageBook sb : storageBooks) {
            if (sb.getStorageId().equals(warehouseId) && sb.getBookId().equals(bookId)) {

                sb.setBookAmount(sb.getBookAmount() + amount);
                return;
            }
        }

        WarehouseBook newBook = WarehouseBook.builder()
                .storageId(warehouseId)
                .bookId(bookId)
                .bookAmount(amount)
                .build();
        storageBooks.add(newBook);
    }

    public void removeBook(Integer bookId, Integer storageId, int amount) {


        for (StorageBook sb : storageBooks) {
            if (sb.getBookId().equals(bookId) && sb.getStorageId().equals(storageId)) {

                if (sb.getBookAmount() >= amount) {
                    sb.setBookAmount(sb.getBookAmount() - amount);
                    if (sb.getBookAmount() == 0) storageBooks.remove(sb);

                }
                else System.out.println("not enough books in warehouse " +  sb.getStorageId()
                        +" всего есть " + sb.getBookAmount());
                return;
            }

        }
    }

    public void addBooks(List<Book> books, Integer storageId) {
        Map<Integer, Integer> bookCountMap = new HashMap<>();

        for (Book book : books) {
            bookCountMap.put(book.getId(), bookCountMap.getOrDefault(book.getId(), 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : bookCountMap.entrySet()) {
            Integer bookId = entry.getKey();
            Integer amount = entry.getValue();

            addBook(bookId, storageId, amount);
        }
    }

    public Integer getAmountOfBook(Integer bookId, Integer warehouseId) {
        for (StorageBook sb : storageBooks)
            if (sb.getBookId().equals(bookId) && sb.getStorageId().equals(warehouseId))
                return sb.getBookAmount();
        return 0;
    }
}
