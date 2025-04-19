package ru.ifellow.jschool.machmetshin.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;

@DataJpaTest
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
