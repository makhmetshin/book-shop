package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.ifellow.jschool.machmetshin.dao.BookDao;
import ru.ifellow.jschool.machmetshin.dao.WarehouseBookDao;
import ru.ifellow.jschool.machmetshin.entity.Book;
import ru.ifellow.jschool.machmetshin.entity.StorageBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
@Service
public class WarehouseService {

    private WarehouseBookService warehouseBookService;
    private BookService bookService;


    public Set<Book> findAllBooks(Integer warehouseId) {
        Set<Book> books = bookService.findAll();

        Set<StorageBook> warehouseBooks = warehouseBookService.findAll();

        Set<Integer> idsBooksInWarehouse =  warehouseBooks.stream()
                .filter(wb -> wb.getStorageId().equals(warehouseId) )
                .map(StorageBook::getBookId)
                .collect(Collectors.toSet());

        return books.stream()
                .filter(book -> idsBooksInWarehouse.contains(book.getId()))
                .collect(Collectors.toSet());
    }



    public void addBook(Integer bookId, Integer warehouseId, int amount) {
        warehouseBookService.addBook(bookId, warehouseId, amount);
    }

    public void removeBook(Integer id, Integer warehouseId, int amount) {
        warehouseBookService.removeBook(id, warehouseId, amount);
    }

    public void addBooks(List<Book> books, Integer warehouseId) {
        warehouseBookService.addBooks(books, warehouseId);
    }

    public Optional<? extends StorageBook> findWarehouseBookByIds(Integer bookId, Integer warehouseId) {
        return warehouseBookService.findByWarehouseIdAndBookId(warehouseId, bookId);
    }

}
