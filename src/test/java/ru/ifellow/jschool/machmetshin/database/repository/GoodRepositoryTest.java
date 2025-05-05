package ru.ifellow.jschool.machmetshin.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.config.ApplicationConfiguration;
import ru.ifellow.jschool.machmetshin.config.jpa.DataInitializer;
import ru.ifellow.jschool.machmetshin.entity.good.Good;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
public class GoodRepositoryTest {
    @Autowired
    private GoodRepository goodRepository;


    @Test
    public void findAllTest() {
        Assertions.assertThat(goodRepository.findAll()).hasSize(10);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(goodRepository.findById(1).get().getDescription()).isEqualTo("Science fiction about space");
        Assertions.assertThat(goodRepository.findById(5).get().getDescription()).isEqualTo("Fantasy epic");
        Assertions.assertThat(goodRepository.findById(10).get().getDescription()).isEqualTo("Contemporary novel");
    }

    @Test
    public void findBySaveTest() {
        Book book = new Book();
        book.setPrice(100);
        book.setDescription("description");
        goodRepository.save(book);
        Assertions.assertThat(goodRepository.findAll()).hasSize(11);
    }

    @Test
    public void deleteTest() {
        Good good = goodRepository.findById(1).get();
        goodRepository.delete(good);
        Assertions.assertThat(goodRepository.findAll()).hasSize(9);
    }
}
