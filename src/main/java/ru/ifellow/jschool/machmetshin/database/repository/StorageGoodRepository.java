package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageGood;

import java.util.List;
import java.util.Optional;

public interface StorageGoodRepository extends JpaRepository<StorageGood, Integer> {
    @EntityGraph(attributePaths = {"good", "storage"})
    Optional<StorageGood> findByStorageIdAndGoodId(Integer storageId, Integer goodId);

    @EntityGraph(attributePaths = {"good", "storage"})
    List<StorageGood> findByStorageId(Integer storageId);
}
