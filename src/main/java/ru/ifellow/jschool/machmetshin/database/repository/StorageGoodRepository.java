package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageGood;

import java.util.List;
import java.util.Optional;

public interface StorageGoodRepository extends JpaRepository<StorageGood, Integer> {
    Optional<StorageGood> findByStorageIdAndGoodId(Integer storageId, Integer goodId);
}
