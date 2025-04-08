package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Book;
import org.example.entity.StorageBook;
import org.example.entity.WarehouseBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

public class StorageBookDao {
    private Set<StorageBook> storageBooks;

    public StorageBookDao(Set<StorageBook> storageBooks) {
        this.storageBooks = storageBooks;
    }

    public StorageBookDao () {}

    public Set<StorageBook> findAll() {
        return storageBooks;
    }

    public Optional<StorageBook> findByStorageIdAndBookId(Integer storageId, Integer bookId) {

        for (StorageBook sb : storageBooks)
            if (sb.getStorageId().equals(storageId) && sb.getBookId().equals(bookId))
                return Optional.of(sb);

        return Optional.empty();
    }

    public boolean addExistingBook(Integer bookId, Integer storageId, int amount) {

        for (StorageBook sb : storageBooks)
            if (sb.getStorageId().equals(storageId) && sb.getBookId().equals(bookId)) {

                sb.setBookAmount(sb.getBookAmount() + amount);
                return true;
            }
        return false;
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


    public Integer getAmountOfBook(Integer bookId, Integer storageId) {
        for (StorageBook sb : storageBooks)
            if (sb.getBookId().equals(bookId) && sb.getStorageId().equals(storageId))
                return sb.getBookAmount();
        return 0;
    }
}
