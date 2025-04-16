package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
