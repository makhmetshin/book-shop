package org.example.service;

import org.example.dao.BookDao;
import org.example.entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopServiceTest {

    ShopService shopService = ShopService.getInstance();
    BookDao bookDao = BookDao.getInstance();

    @Test
    public void sellBookTest() {
        Map<Integer, Integer> books = new HashMap<>();
        books.put(1, 10);
        books.put(5, 20);
        books.put(10, 100);

        Bill bill = shopService.sellBooks(books, 1, "ivan");
        assertThat(shopService.findShopBookByIds(1,1).get().getBookAmount())
                .isEqualTo(990);
        assertThat(shopService.findShopBookByIds(5,1).get().getBookAmount())
                .isEqualTo(980);
        assertThat(shopService.findShopBookByIds(10,1).get().getBookAmount())
                .isEqualTo(900);

        assertThat(bill.getItems().size()).isEqualTo(3);
    }

    @Test
    public void distributeBooksTest() {
        List<Integer> shopIds = new ArrayList<>();
        shopIds.add(1);
        shopIds.add(2);
        shopIds.add(3);
        shopService.distributeBooks(1, shopIds, 1, 0);

        assertThat(shopService.findShopBookByIds(1, 1).get().getBookAmount())
                .isEqualTo(1000);
        assertThat(shopService.findShopBookByIds(1, 2).get().getBookAmount())
                .isEqualTo(2000);
        assertThat(shopService.findShopBookByIds(1, 3).get().getBookAmount())
                .isEqualTo(3000);

        shopService.distributeBooks(1, shopIds, 1, 700);

        assertThat(shopService.findShopBookByIds(1, 1).get().getBookAmount())
                .isEqualTo(1234);
        assertThat(shopService.findShopBookByIds(1, 2).get().getBookAmount())
                .isEqualTo(2233);
        assertThat(shopService.findShopBookByIds(1, 3).get().getBookAmount())
                .isEqualTo(3233);

    }

    @Test
    public void distributeBooksAndLeftWarehouseEmptyTest() {
        List<Integer> shopIds = new ArrayList<>();
        List<ShopBook> shopBooks = new ArrayList<>();
        shopIds.add(1);
        shopIds.add(2);
        shopIds.add(3);
        shopService.distributeBooks(1, shopIds, 1, 1000);

        assertThat(shopService.findShopBookByIds(1, 1).get().getBookAmount())
                .isEqualTo(1334);
        assertThat(shopService.findShopBookByIds(1, 2).get().getBookAmount())
                .isEqualTo(2333);
        assertThat(shopService.findShopBookByIds(1, 3).get().getBookAmount())
                .isEqualTo(3333);

    }

    @Test
    public void transportBooksFromWarehouseTest() {
        shopService.transportBooksFromWarehouse(1, 1, 1,100);
        assertThat(shopService.findShopBookByIds(1,1).get().getBookAmount())
                .isEqualTo(1100);
        assertThat(shopService.findWarehouseBookByIds(1,1).get().getBookAmount())
                .isEqualTo(900);
    }

    @Test
    public void findBooksByGenreAndAuthorTest() {
        assertThat(shopService.findBooksByGenreAndAuthor("CookBook", "Author Nine").size())
                .isEqualTo(1);

        assertThat(shopService.findBooksByAuthor("Author Three").size())
                .isEqualTo(3);
        assertThat(shopService.findBooksByAuthor("Author One").size())
                .isEqualTo(1);

        assertThat(shopService.findBooksByGenre("Biography").size())
                .isEqualTo(2);
        assertThat(shopService.findBooksByGenre("Romance").size())
                .isEqualTo(2);

    }

    @Test
    public void findBookByAuthorAndTitleTest() {
        assertThat(shopService.findBookByAuthorAndTitle("Author One", "Book One").size())
                .isEqualTo(1);

        assertThat(shopService.findBookByAuthorAndTitle("One", "One").size())
                .isEqualTo(1);
        assertThat(shopService.findBookByAuthorAndTitle("Author", "Book").size())
                .isEqualTo(10);

    }

    @Test
    public void findShopBookByIdsTest() {
        assertThat(shopService.findShopBookByIds(1, 1).get().getBookAmount())
                .isEqualTo(1000);
        assertThat(shopService.findShopBookByIds(1, 5).get().getBookAmount())
                .isEqualTo(5000);
        assertThat(shopService.findShopBookByIds(1, 10).get().getBookAmount())
                .isEqualTo(10000);
    }

    @Test
    public void findWarehouseBookByIdsTest() {
        assertThat(shopService.findWarehouseBookByIds(1, 1).get().getBookAmount())
                .isEqualTo(1000);
        assertThat(shopService.findWarehouseBookByIds(1, 5).get().getBookAmount())
                .isEqualTo(5000);
        assertThat(shopService.findWarehouseBookByIds(1, 10).get().getBookAmount())
                .isEqualTo(10000);
    }








}
