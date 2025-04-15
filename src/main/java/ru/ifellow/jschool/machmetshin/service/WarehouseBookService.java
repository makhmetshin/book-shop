package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.dao.WarehouseBookDao;
import ru.ifellow.jschool.machmetshin.entity.Book;
import ru.ifellow.jschool.machmetshin.entity.StorageBook;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class WarehouseBookService {

    private WarehouseBookDao warehouseBookDao;

    public Set<StorageBook> findAll() {
        return warehouseBookDao.findAll();
    }

    public void addBook(Integer bookId, Integer warehouseId, int amount) {
        warehouseBookDao.addBook(bookId, warehouseId, amount);
    }

    public void addBooks(List<Book> books, Integer warehouseId) {
        warehouseBookDao.addBooks(books, warehouseId);
    }

    public void removeBook(Integer bookId, Integer warehouseId, int amount) {
        warehouseBookDao.removeBook(bookId, warehouseId, amount);
    }

    public Optional<? extends StorageBook> findByWarehouseIdAndBookId(Integer warehouseId, Integer bookId) {
        return warehouseBookDao.findByStorageIdAndBookId(warehouseId, bookId);
    }

    public Integer getAmountOfBook(Integer bookId, Integer warehouseId) {
        return warehouseBookDao.getAmountOfBook(bookId, warehouseId);
    }

}
