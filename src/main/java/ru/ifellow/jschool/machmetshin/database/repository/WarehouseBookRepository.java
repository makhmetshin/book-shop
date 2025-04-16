package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.WarehouseBook;

public interface WarehouseBookRepository extends JpaRepository<WarehouseBook, Integer> {
}
