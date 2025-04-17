package ru.ifellow.jschool.machmetshin.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.ShopBookRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.storage.ShopBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Transactional
public class ShopBookService {

    private ShopBookRepository shopBookRepository;
    private ShopService shopService;
    private BookService bookService;

    public int removeBook(Integer bookId, Integer shopId, int amount) {

        ShopBook shopBook = shopBookRepository.findByShopIdAndBookId(shopId, bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found in this shop"));

        int currentAmount = shopBook.getBookAmount();

        if (currentAmount <= amount)
            shopBookRepository.delete(shopBook);
        else {
            shopBook.setBookAmount(currentAmount - amount);
            shopBookRepository.save(shopBook);
        }
        return currentAmount < amount ? currentAmount : amount;
    }

    public void addBook(Integer bookId, Integer shopId, int amount) {
        ShopBook shopBook;
        Optional<ShopBook> optionalShopBook = shopBookRepository.findByShopIdAndBookId(shopId, bookId);

        if (optionalShopBook.isPresent()) {
            shopBook = optionalShopBook.get();
            shopBook.setBookAmount(shopBook.getBookAmount() + amount);
        }
        else
            shopBook = ShopBook.builder()
                    .shop(shopService.findById(shopId)
                            .orElseThrow(() -> new EntityNotFoundException("There is no such shop with id %d".formatted(shopId)))
                    )
                    .book(bookService.findById(bookId)
                            .orElseThrow(() -> new EntityNotFoundException("There is no such book with id %d".formatted(bookId)))
                    )
                    .bookAmount(amount)
                    .build();

        shopBookRepository.save(shopBook);
    }

    public void addBooks(List<Book> books, Integer shopId) {
        Map<Integer, Integer> bookCountMap = new HashMap<>();

        for (Book book : books)
            bookCountMap.put(book.getId(), bookCountMap.getOrDefault(book.getId(), 0) + 1);

        for (Map.Entry<Integer, Integer> entry : bookCountMap.entrySet())
            addBook(entry.getKey(), shopId, entry.getValue());
    }

    public Integer getAmountOfBook(Integer bookId, Integer shopId) {
        Optional<ShopBook> optionalShopBook = shopBookRepository.findByShopIdAndBookId(bookId, shopId);

        if (optionalShopBook.isPresent()) return  optionalShopBook.get().getBookAmount();
        else throw new EntityNotFoundException("There is no such shop with id %d or such book with id $d in the shop".formatted(shopId, bookId));
    }

    public Optional<ShopBook> findByShopIdAndBookId(Integer shopId, Integer bookId) {
        return shopBookRepository.findByShopIdAndBookId(shopId, bookId);
    }

}
