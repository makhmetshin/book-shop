package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.ShopBook;

public interface ShopBookRepository extends JpaRepository<ShopBook, Integer> {
}
