package ru.ifellow.jschool.machmetshin.database.repository;


import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;

public interface StorageRepository extends JpaRepository<Storage, Integer> {
}
