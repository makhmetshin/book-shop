package ru.ifellow.jschool.machmetshin.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.config.ApplicationConfiguration;
import ru.ifellow.jschool.machmetshin.config.DataInitializer;
import ru.ifellow.jschool.machmetshin.config.JpaConfiguration;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;


@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    public void findAllTest() {
        Assertions.assertThat(authorRepository.findAll()).hasSize(3);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(authorRepository.findById(1).get().getFirstName()).isEqualTo("Leo");
        Assertions.assertThat(authorRepository.findById(2).get().getFirstName()).isEqualTo("Fyodor");
        Assertions.assertThat(authorRepository.findById(3).get().getFirstName()).isEqualTo("Anton");
    }

    @Test
    public void findBySaveTest() {
        authorRepository.save(new Author());
        Assertions.assertThat(authorRepository.findAll()).hasSize(4);
    }

    @Test
    public void deleteTest() {
        Author author =authorRepository.findById(1).get();
        authorRepository.delete(author);
        Assertions.assertThat(authorRepository.findAll()).hasSize(2);
    }



}
