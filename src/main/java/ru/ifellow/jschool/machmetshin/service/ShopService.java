package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.dao.*;
import ru.ifellow.jschool.machmetshin.entity.Bill;
import ru.ifellow.jschool.machmetshin.entity.Book;
import ru.ifellow.jschool.machmetshin.entity.Shop;
import ru.ifellow.jschool.machmetshin.entity.StorageBook;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ShopService {

    private int orderIdSequence = 1;

    private ShopBookDao shopBookService;
    private WarehouseBookService warehouseBookService;
    private BillDao billService;
    private BookService bookService;
    private ShopDao shopDao;



    public Set<Shop> findAllShops() {
        return shopDao.findAll();
    }

    public Bill sellBooks(Map<Integer, Integer> items, Integer shopId, String customerFio ) {

        Shop shop = shopDao.findById(shopId).get();
        Integer summa = 0;
        for( Map.Entry<Integer, Integer> entry : items.entrySet() ) {

            int bookId = entry.getKey();
            int amount = entry.getValue();

            int bookPrice = bookService.findById(bookId).get().getPrice();
            shopBookService.removeBook(bookId, shopId, amount);
            summa += amount * bookPrice;
        }

        Bill bill = Bill.builder()
                .id(orderIdSequence++)
                .shopId(shopId)
                .items(items)
                .customerFio(customerFio)
                .date(LocalDate.now())
                .summa(summa)
                .build();
        billService.save(bill);

        return bill;

    }

    public void distributeBooks( Integer bookId, List<Integer> shopIds, Integer warehouseId, Integer amount) {

        if (shopIds.isEmpty()) {
            System.out.println("shopIds is empty in distributeBooks()");
            return;
        }

        int booksPerShop = amount / shopIds.size();
        int remainder = amount % shopIds.size();
        Integer firstShopId = 0;


        transportBooksFromWarehouse(bookId, warehouseId, shopIds.get(firstShopId), remainder);

        for (Integer shopId : shopIds)
            transportBooksFromWarehouse(bookId, warehouseId, shopId, booksPerShop);

    }

    public void transportBooksFromWarehouse(Integer bookId, Integer warehouseId, Integer shopId, Integer amount) {
        warehouseBookService.removeBook(bookId, warehouseId, amount);
        shopBookService.addBook(bookId, shopId, amount);
    }

    public Set<Book> findBooksByGenreAndAuthor(String genre, String author) {
        return bookService.findBooksByGenreAndAuthor(genre, author);
    }

    public Set<Book> findBooksByGenre(String genre) {
        return bookService.findBooksByGenre(genre);
    }

    public Set<Book> findBooksByAuthor(String author) {
        return bookService.findBooksByAuthor(author);
    }

    public Set<Book> findBooksByAuthorAndTitle(String author, String title) {
        return bookService.findBooksByAuthorAndTitle(author, title);
    }

    public Optional<? extends StorageBook> findShopBookByIds(Integer bookId, Integer shopId) {
        return shopBookService.findByShopIdAndBookId(shopId, bookId);
    }

    public Optional<? extends StorageBook> findWarehouseBookByIds(Integer bookId, Integer warehouseId) {
        return warehouseBookService.findByWarehouseIdAndBookId(warehouseId, bookId);
    }

}
