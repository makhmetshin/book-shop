package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;
import ru.ifellow.jschool.machmetshin.entity.storage.WarehouseBook;

import java.util.Optional;

public interface WarehouseBookRepository extends JpaRepository<WarehouseBook, Integer> {

    Optional<WarehouseBook> findByWarehouseIdAndBookId(Integer warehouseId, Integer bookId);

}
