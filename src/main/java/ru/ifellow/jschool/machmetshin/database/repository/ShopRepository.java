package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
