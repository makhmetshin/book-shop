package ru.ifellow.jschool.machmetshin.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.WarehouseBookRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;

import ru.ifellow.jschool.machmetshin.entity.storage.WarehouseBook;


import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Transactional
public class WarehouseBookService {

    private WarehouseBookRepository warehouseBookRepository;
    private WarehouseService warehouseService;
    private BookService bookService;

    // Тут все комменты будут аналогичны комментам ShopBookService, так как методы идентичны.
    // Кстати, это показатель того, что где-то произошло нарушение правила DRY (don't repeat yourself),
    // стоит поразмышлять над унесением функционала в одно место.

    public List<WarehouseBook> findAll() {
        return warehouseBookRepository.findAll();
    }

    public void addBook(Integer bookId, Integer warehouseId, int amount) {
        WarehouseBook warehouseBook;
        Optional<WarehouseBook> optionalWarehouseBook = warehouseBookRepository.findByWarehouseIdAndBookId(warehouseId, bookId);

        if (optionalWarehouseBook.isPresent()) {
            warehouseBook = optionalWarehouseBook.get();
            warehouseBook.setBookAmount(warehouseBook.getBookAmount() + amount);
        }
        else
            warehouseBook= WarehouseBook.builder()
                    .warehouse(warehouseService.findById(warehouseId)
                            .orElseThrow(() -> new EntityNotFoundException("There is no such shop with id %d".formatted(warehouseId)))
                    )
                    .book(bookService.findById(bookId)
                            .orElseThrow(() -> new EntityNotFoundException("There is no such book with id %d".formatted(bookId)))
                    )
                    .bookAmount(amount)
                    .build();

        warehouseBookRepository.save(warehouseBook);
    }

    public void addBooks(List<Book> books, Integer warehouseId) {
        Map<Integer, Integer> bookCountMap = new HashMap<>();

        for (Book book : books)
            bookCountMap.put(book.getId(), bookCountMap.getOrDefault(book.getId(), 0) + 1);

        for (Map.Entry<Integer, Integer> entry : bookCountMap.entrySet())
            addBook(entry.getKey(), warehouseId, entry.getValue());
    }

    public int removeBook(Integer bookId, Integer warehouseId, int amount) {
        WarehouseBook warehouseBook = warehouseBookRepository.findByWarehouseIdAndBookId(warehouseId, bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found in this shop"));

        int currentAmount = warehouseBook.getBookAmount();

        if (currentAmount <= amount)
            warehouseBookRepository.delete(warehouseBook);
        else {
            warehouseBook.setBookAmount(currentAmount - amount);
            warehouseBookRepository.save(warehouseBook);
        }
        return currentAmount < amount ? currentAmount : amount;
    }

    public Optional<WarehouseBook> findByWarehouseIdAndBookId(Integer warehouseId, Integer bookId) {
        return warehouseBookRepository.findByWarehouseIdAndBookId(warehouseId, bookId);
    }

    public Integer getAmountOfBook(Integer bookId, Integer warehouseId) {
        Optional<WarehouseBook> optionalWarehouseBook = warehouseBookRepository.findByWarehouseIdAndBookId(bookId, warehouseId);

        if (optionalWarehouseBook.isPresent()) return  optionalWarehouseBook.get().getBookAmount();
        else throw new EntityNotFoundException("There is no such warehouse with id %d or such book with id $d in the warehouse".formatted(warehouseId, bookId));
    }

}
