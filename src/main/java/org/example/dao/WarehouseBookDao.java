package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Book;
import org.example.entity.ShopBook;
import org.example.entity.Warehouse;
import org.example.entity.WarehouseBook;

import java.util.*;

public class WarehouseBookDao {

        private DataSource dataSource = DataSource.getInstance();
        private BookDao bookDao = BookDao.getInstance();

        private final static WarehouseBookDao INSTANCE = new WarehouseBookDao();
        private Set<WarehouseBook> warehouseBooks = dataSource.getWarehouseBooks();
        private WarehouseBookDao () {}

        public static WarehouseBookDao getInstance() {
            return new WarehouseBookDao();
//            return INSTANCE;
        }


        public Set<WarehouseBook> findAll() {
            return dataSource.getWarehouseBooks();

        }

        public Optional<WarehouseBook> findByWarehouseIdAndBookId(Integer warehouseId, Integer bookId) {

            for (WarehouseBook wb : warehouseBooks)
                if (wb.getWarehouseId().equals(warehouseId) && wb.getBookId().equals(bookId))
                    return Optional.of(wb);

            return Optional.empty();
        }

        public void addBook(Integer bookId, Integer warehouseId, int amount) {


            for (WarehouseBook wb : warehouseBooks) {
                if (wb.getWarehouseId().equals(warehouseId) && wb.getBookId().equals(bookId)) {

                    wb.setBookAmount(wb.getBookAmount() + amount);
                    return;
                }
            }

            WarehouseBook newBook = WarehouseBook.builder()
                    .warehouseId(warehouseId)
                    .bookId(bookId)
                    .bookAmount(amount)
                    .build();
            warehouseBooks.add(newBook);
        }

        public void removeBook(Integer bookId, Integer warehouseId, int amount) {


            for (WarehouseBook wb : warehouseBooks) {
                if (wb.getBookId().equals(bookId) && wb.getWarehouseId().equals(warehouseId)) {

                    if (wb.getBookAmount() >= amount) {
                        wb.setBookAmount(wb.getBookAmount() - amount);
                        if (wb.getBookAmount() == 0) warehouseBooks.remove(wb);

                    }
                    else System.out.println("not enough books in warehouse " +  wb.getWarehouseId() +" всего есть " + wb.getBookAmount());
                    return;
                }

            }
        }

        public void addBooks(List<Book> books, Integer warehouseId) {
            Map<Integer, Integer> bookCountMap = new HashMap<>();

            for (Book book : books) {
                bookCountMap.put(book.getId(), bookCountMap.getOrDefault(book.getId(), 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : bookCountMap.entrySet()) {
                Integer bookId = entry.getKey();
                Integer amount = entry.getValue();

                addBook(bookId, warehouseId, amount);
            }
        }

        public Integer getAmountOfBook(Integer bookId, Integer warehouseId) {
            for (WarehouseBook wb : warehouseBooks)
                if (wb.getBookId().equals(bookId) && wb.getWarehouseId().equals(warehouseId))
                    return wb.getBookAmount();
            return 0;
        }






}
