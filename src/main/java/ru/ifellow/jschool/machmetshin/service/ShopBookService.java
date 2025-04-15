package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.dao.ShopBookDao;
import ru.ifellow.jschool.machmetshin.entity.StorageBook;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ShopBookService {

    private ShopBookDao shopBookDao;

    public void removeBook(Integer bookId, Integer shopId, int amount) {
        shopBookDao.removeBook(bookId, shopId, amount);
    }
    public void addBook(Integer bookId, Integer shopId, int amount) {
        shopBookDao.addBook(bookId, shopId, amount);
    }
    public Optional<? extends StorageBook> findByShopIdAndBookId(Integer shopId, Integer bookId) {
        return shopBookDao.findByStorageIdAndBookId(shopId, bookId);
    }

}
