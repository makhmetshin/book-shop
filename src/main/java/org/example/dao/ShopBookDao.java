package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Book;
import org.example.entity.ShopBook;
import org.example.entity.WarehouseBook;
import org.example.service.ShopService;

import java.util.Optional;
import java.util.Set;

public class ShopBookDao {

    private DataSource dataSource = DataSource.getInstance();
    private BookDao bookDao = BookDao.getInstance();

    private final static ShopBookDao INSTANCE = new ShopBookDao();
    private Set<ShopBook> shopBooks = dataSource.getShopBooks();
    private ShopBookDao () {}

    public static ShopBookDao getInstance() {
        return new ShopBookDao();
//        return INSTANCE;
    }

    public Set<ShopBook> findAll() {
        return shopBooks;
    }

    public void addBook(Integer bookId, Integer shopId, int amount) {

        for (ShopBook sb : shopBooks) {
            if (sb.getShopId().equals(shopId) && sb.getBookId().equals(bookId)) {
                sb.setBookAmount(sb.getBookAmount() + amount);
                return;
            }
        }

        ShopBook newBook = ShopBook.builder()
                .shopId(shopId)
                .bookId(bookId)
                .bookAmount(amount)
                .build();
        shopBooks.add(newBook);
    }

    public void removeBook(Integer bookId, Integer shopId, int amount) {

        for (ShopBook sb : shopBooks) {
            if (sb.getBookId().equals(bookId) && sb.getShopId().equals(shopId)) {

                if (sb.getBookAmount() >= amount) {

                    sb.setBookAmount(sb.getBookAmount() - amount);
                    if (sb.getBookAmount() == 0) shopBooks.remove(sb);

                }
                else System.out.println("Недостаточно книг в магазине " +  sb.getShopId() +" всего есть " + sb.getBookAmount());

                return;
            }
        }
    }

    public Optional<ShopBook> findByShopIdAndBookId(Integer shopId, Integer bookId) {

        for (ShopBook sb : shopBooks)
            if (sb.getShopId().equals(shopId) && sb.getBookId().equals(bookId))
                return Optional.of(sb);

        return Optional.empty();
    }


}
