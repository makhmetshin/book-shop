package ru.ifellow.jschool.machmetshin.database.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.good.Good;

public interface GoodRepository extends JpaRepository<Good, Integer> {
}
