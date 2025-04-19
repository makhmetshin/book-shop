package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.storage.WarehouseBook;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class WarehouseManagerService {

    private WarehouseBookService warehouseBookService;
    private BookService bookService;

    public Set<Book> findAllBooks(Integer warehouseId) {
        List<Book> books = bookService.findAll();

        List<WarehouseBook> warehouseBooks = warehouseBookService.findAll();

        Set<Integer> idsBooksInWarehouse =  warehouseBooks.stream()
                .filter(wb -> wb.equals(warehouseId) )
                .map(wb -> wb.getBook().getId())
                .collect(Collectors.toSet());

        return books.stream()
                .filter(book -> idsBooksInWarehouse.contains(book.getId()))
                .collect(Collectors.toSet());
    }


    @Transactional
    public void addBook(Integer bookId, Integer warehouseId, int amount) {
        warehouseBookService.addBook(bookId, warehouseId, amount);
    }

    @Transactional
    public void removeBook(Integer id, Integer warehouseId, int amount) {
        warehouseBookService.removeBook(id, warehouseId, amount);
    }
    @Transactional
    public void addBooks(List<Book> books, Integer warehouseId) {
        warehouseBookService.addBooks(books, warehouseId);
    }

    public Optional<WarehouseBook> findWarehouseBookByIds(Integer bookId, Integer warehouseId) {
        return warehouseBookService.findByWarehouseIdAndBookId(warehouseId, bookId);
    }

}
