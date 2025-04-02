package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Book;
import org.example.entity.ShopBook;
import org.example.entity.StorageBook;
import org.example.entity.WarehouseBook;
import org.example.service.ShopService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ShopBookDao extends StorageBookDao{

    private DataSource dataSource = DataSource.getInstance();

    private final static ShopBookDao INSTANCE = new ShopBookDao();
    private Set<StorageBook> warehouseBooks = dataSource.getWarehouseBooks();

    private ShopBookDao () {
        super(DataSource.getInstance().getWarehouseBooks());
    }


    public static ShopBookDao getInstance() {
        return new ShopBookDao();
//            return INSTANCE;
    }


    public Set<StorageBook> findAll() {
        return super.findAll();
    }

    public Optional<? extends StorageBook> findByShopIdAndBookId(Integer warehouseId, Integer bookId) {
        return super.findByStorageIdAndBookId(warehouseId, bookId);
    }

    public void addBook(Integer bookId, Integer warehouseId, int amount) {
        super.addBook(bookId, warehouseId, amount);
    }

    public void removeBook(Integer bookId, Integer warehouseId, int amount) {
        super.removeBook(bookId, warehouseId, amount);
    }

    public void addBooks(List<Book> books, Integer warehouseId) {
        super.addBooks(books, warehouseId);
    }

    public Integer getAmountOfBook(Integer bookId, Integer warehouseId) {
        return super.getAmountOfBook(bookId, warehouseId);
    }

}
