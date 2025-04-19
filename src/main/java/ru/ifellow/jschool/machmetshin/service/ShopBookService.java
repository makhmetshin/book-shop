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

        if (currentAmount <= amount) //вот тут как раз просится проверка на то, что хотим удалить не больше, чем есть в магазине
            shopBookRepository.delete(shopBook);
        else {
            shopBook.setBookAmount(currentAmount - amount);
            shopBookRepository.save(shopBook);
        }
        return currentAmount < amount ? currentAmount : amount; //можно сделать return-ы внутри if-else, чтобы такую конструкцию не городить
    }

    public void addBook(Integer bookId, Integer shopId, int amount) {
        //переписала немножко метод, как думаешь, симпатичнее получилось?
        ShopBook shopBook = shopBookRepository.findByShopIdAndBookId(shopId, bookId)
                .map(sb -> {
                    sb.setBookAmount(sb.getBookAmount() + amount);
                    return sb;
                })
                // вот тут важно именно orElseGet, а не orElse!
                .orElseGet(() -> ShopBook.builder()
                        .shop(shopService.findById(shopId)
                                .orElseThrow(() -> new EntityNotFoundException("There is no such shop with id %d".formatted(shopId)))
                        )
                        .book(bookService.findById(bookId)
                                .orElseThrow(() -> new EntityNotFoundException("There is no such book with id %d".formatted(bookId)))
                        )
                        .bookAmount(amount)
                        .build());

        shopBookRepository.save(shopBook);
    }

    public void addBooks(List<Book> books, Integer shopId) {
        //в этом методе тоже можно со StreamAPI развернуться
        //попробуй заюзать .collect(Collectors.groupingBy(...)), очень мощная штука
        Map<Integer, Integer> bookCountMap = new HashMap<>();

        for (Book book : books)
            bookCountMap.put(book.getId(), bookCountMap.getOrDefault(book.getId(), 0) + 1);

        for (Map.Entry<Integer, Integer> entry : bookCountMap.entrySet())
            addBook(entry.getKey(), shopId, entry.getValue());
    }

    public Integer getAmountOfBook(Integer bookId, Integer shopId) {
        //тут тоже немного перепишу, погляди, вроде лаконичнее стало?
        return shopBookRepository.findByShopIdAndBookId(bookId, shopId)
                .map(ShopBook::getBookAmount)
                .orElseThrow(() -> new EntityNotFoundException("There is no such shop with id %d or such book with id $d in the shop".formatted(shopId, bookId)));
    }

    public Optional<ShopBook> findByShopIdAndBookId(Integer shopId, Integer bookId) {
        return shopBookRepository.findByShopIdAndBookId(shopId, bookId);
    }
}
