package ru.ifellow.jschool.machmetshin.database.repository;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;

import java.util.List;
import java.util.Set;


@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void findAllTest() {
        Assertions.assertThat(bookRepository.findAll()).hasSize(10);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(bookRepository.findById(1).get().getAuthor().getId()).isEqualTo(1);
        Assertions.assertThat(bookRepository.findById(5).get().getAuthor().getId()).isEqualTo(2);
        Assertions.assertThat(bookRepository.findById(10).get().getAuthor().getId()).isEqualTo(3);
    }
    @Test
    public void findBySaveTest() {
        bookRepository.save(new Book());
        Assertions.assertThat(bookRepository.findAll()).hasSize(11);
    }

    @Test
    public void deleteTest() {
        Book book = bookRepository.findById(1).get();
        bookRepository.delete(book);
        Assertions.assertThat(bookRepository.findAll()).hasSize(9);
    }

    @Test
    public void findBookByGenreAndAuthorTest() {
        Assertions.assertThat(bookRepository.findBookByGenreAndAuthor(
                "Romance",
                authorRepository.findById(1).get()).size()).isEqualTo(1);

    }

    @Test
    public void findBookByGenreTest() {
        Assertions.assertThat(bookRepository.findBookByGenre("Fantasy")).hasSize(1);
    }

    @Test
    public void findBookByAuthorTest() {

        Assertions.assertThat(bookRepository.findBookByAuthor(
                authorRepository.findById(1).get()).size())
                .isEqualTo(3);
    }

    @Test
    public void findBookByAuthorAndTitleContainingIgnoreCaseTest() {
        Assertions.assertThat(bookRepository.findBookByAuthorAndTitleContainingIgnoreCase(
                authorRepository.findById(1).get(), "Lov")).hasSize(1);
    }




}
