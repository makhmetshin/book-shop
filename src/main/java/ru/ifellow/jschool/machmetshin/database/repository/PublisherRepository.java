package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {


}
