package org.example.service;

import org.example.dao.BookDao;
import org.example.dao.WarehouseBookDao;
import org.example.dao.WarehouseDao;
import org.example.entity.Book;
import org.example.entity.StorageBook;
import org.example.entity.WarehouseBook;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class WarehouseService {

    private WarehouseDao warehouseDao = WarehouseDao.getInstance();
    private WarehouseBookDao warehouseBookDao = WarehouseBookDao.getInstance();
    private BookDao bookDao = BookDao.getInstance();

    private final static WarehouseService  INSTANCE = new WarehouseService ();
    private WarehouseService () {}

    public static WarehouseService  getInstance() {
        return new WarehouseService ();
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
