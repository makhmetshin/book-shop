package ru.ifellow.jschool.machmetshin.service;

import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WarehouseServiceTest {

    WarehouseService warehouseService = WarehouseService.getInstance();


    @Test
    public void findAllBooksTest() {
        assertThat(warehouseService.findAllBooks(1).size())
                .isEqualTo(10);
        assertThat(warehouseService.findAllBooks(5).size())
                .isEqualTo(10);
        assertThat(warehouseService.findAllBooks(10).size())
                .isEqualTo(10);
    }

    @Test
    public void addBookTest() {
        warehouseService.addBook(1,1,100);
        assertThat(warehouseService.findWarehouseBookByIds(1,1).get().getBookAmount())
                .isEqualTo(1100);

        warehouseService.addBook(1,5,5000);
        assertThat(warehouseService.findWarehouseBookByIds(1,5).get().getBookAmount())
                .isEqualTo(10000);

        warehouseService.addBook(1,10,5000);
        assertThat(warehouseService.findWarehouseBookByIds(1,10).get().getBookAmount())
                .isEqualTo(15000);

    }

    @Test
    public void removeBookTest() {
        warehouseService.removeBook(1,1, 100);
        assertThat(warehouseService.findWarehouseBookByIds(1,1).get().getBookAmount())
                .isEqualTo(900);
        warehouseService.removeBook(1,1, 500);
        assertThat(warehouseService.findWarehouseBookByIds(1,1).get().getBookAmount())
                .isEqualTo(400);
        warehouseService.removeBook(1,1, 400);
        assertThat(warehouseService.findWarehouseBookByIds(1,1).get().getBookAmount())
                .isEqualTo(0);
    }

    @Test
    public void addBooksTest() {
        List<Book> books = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            books.add(Book.builder().id(1).build());
            books.add(Book.builder().id(2).build());
            books.add(Book.builder().id(3).build());
        }
        warehouseService.addBooks(books, 1);

        assertThat(warehouseService.findWarehouseBookByIds(1,1).get().getBookAmount())
                .isEqualTo(1100);
        assertThat(warehouseService.findWarehouseBookByIds(2,1).get().getBookAmount())
                .isEqualTo(1100);
        assertThat(warehouseService.findWarehouseBookByIds(3,1).get().getBookAmount())
                .isEqualTo(1100);

        books.clear();
        warehouseService.addBooks(books, 1);

        assertThat(warehouseService.findWarehouseBookByIds(1,1).get().getBookAmount())
                .isEqualTo(1100);
        assertThat(warehouseService.findWarehouseBookByIds(2,1).get().getBookAmount())
                .isEqualTo(1100);
        assertThat(warehouseService.findWarehouseBookByIds(3,1).get().getBookAmount())
                .isEqualTo(1100);

    }

    @Test
    public void findWarehouseBookByIdsTest() {
        assertThat(warehouseService.findWarehouseBookByIds(1,5).get().getBookAmount())
                .isEqualTo(5000);
    }

}
