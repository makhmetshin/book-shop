package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.*;

import java.util.*;

public class WarehouseBookDao extends StorageBookDao{

        private DataSource dataSource = DataSource.getInstance();

        private final static WarehouseBookDao INSTANCE = new WarehouseBookDao();
        private Set<StorageBook> warehouseBooks = dataSource.getWarehouseBooks();

        private WarehouseBookDao () {
            super(DataSource.getInstance().getWarehouseBooks());
        }


        public static WarehouseBookDao getInstance() {
            return new WarehouseBookDao();
//            return INSTANCE;
        }


        public Set<StorageBook> findAll() {
            return super.findAll();
        }

        public Optional<? extends StorageBook> findByWarehouseIdAndBookId(Integer warehouseId, Integer bookId) {
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
