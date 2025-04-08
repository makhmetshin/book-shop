package org.example.service;

import org.example.dao.BookDao;
import org.example.dao.WarehouseBookDao;
import org.example.dao.WarehouseDao;
import org.example.entity.Book;
import org.example.entity.StorageBook;
import org.example.entity.WarehouseBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    private WarehouseBookDao warehouseBookDao;
    private BookDao bookDao;

    @Autowired
    public WarehouseService(WarehouseBookDao warehouseBookDao, BookDao bookDao) {
        this.warehouseBookDao = warehouseBookDao;
        this.bookDao = bookDao;
    }

    public WarehouseService () {}

    public static WarehouseService  getInstance() {
        return null;
//        return INSTANCE;
    }

    public Set<Book> findAllBooks(Integer warehouseId) {
        Set<Book> books = bookDao.findAll();

        Set<StorageBook> warehouseBooks = warehouseBookDao.findAll();

        Set<Integer> idsBooksInWarehouse =  warehouseBooks.stream()
                .filter(wb -> wb.getStorageId().equals(warehouseId) )
                .map(StorageBook::getBookId)
                .collect(Collectors.toSet());

        return books.stream()
                .filter(book -> idsBooksInWarehouse.contains(book.getId()))
                .collect(Collectors.toSet());
    }



    public void addBook(Integer bookId, Integer warehouseId, int amount) {
        warehouseBookDao.addBook(bookId, warehouseId, amount);
    }

    public void removeBook(Integer id, Integer warehouseId, int amount) {
        warehouseBookDao.removeBook(id, warehouseId, amount);
    }

    public void addBooks(List<Book> books, Integer warehouseId) {
        warehouseBookDao.addBooks(books, warehouseId);
    }

    public Optional<? extends StorageBook> findWarehouseBookByIds(Integer bookId, Integer warehouseId) {
        return warehouseBookDao.findByWarehouseIdAndBookId(warehouseId, bookId);
    }

}
