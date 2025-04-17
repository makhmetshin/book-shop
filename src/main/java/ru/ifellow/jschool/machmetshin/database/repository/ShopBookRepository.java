package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.ShopBook;

import java.util.Optional;

public interface ShopBookRepository extends JpaRepository<ShopBook, Integer> {
    Optional<ShopBook> findByShopIdAndBookId(Integer shopId, Integer bookId);

}
