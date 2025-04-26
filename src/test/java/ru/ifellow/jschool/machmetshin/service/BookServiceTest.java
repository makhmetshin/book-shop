package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ifellow.jschool.machmetshin.database.repository.BillRepository;
import ru.ifellow.jschool.machmetshin.database.repository.BookRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;

import java.util.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;


    @InjectMocks
    private BookService bookService;

    private List<Book> books = new ArrayList<>();


    @Test
    public void findAllTest() {
        for (int i = 0; i < 11; i++)
            books.add(new Book());

        Mockito.doReturn(books).when(bookRepository).findAll();
        Assertions.assertThat(bookService.findAll()).hasSize(11);
    }

    @Test
    public void findByIdTest() {
        Mockito.doReturn(Optional.ofNullable(Book.builder().genre("comedy").build()) )
                .when(bookRepository).findById(1);

        Assertions.assertThat(bookService.findById(1).get().getGenre())
                .isEqualTo("comedy");
    }

    @Test
    public void findBooksByGenreAndAuthorTest() {
        List<Book> local_books = new ArrayList<>();
        local_books.add(Book.builder().title("title1").genre("comedy").build());
        local_books.add(Book.builder().title("title2").genre("comedy").build());
        Author author = Author.builder().lastName("pushkin").build();

        Mockito.doReturn( local_books)
                .when(bookRepository).findBookByGenreAndAuthor("comedy", author);

        Assertions.assertThat(bookService.findBooksByGenreAndAuthor("comedy", author))
                .hasSize(2);
    }

    @Test
    public void findBooksByAuthorAndTitleTest() {
        List<Book> local_books = new ArrayList<>();
        local_books.add(Book.builder().title("title1").genre("comedy").build());
        local_books.add(Book.builder().title("title2").genre("comedy").build());
        Author author = Author.builder().lastName("pushkin").build();

        Mockito.doReturn( local_books)
                .when(bookRepository).findBookByAuthorAndTitleContainingIgnoreCase(
                        author,"title1");

        Assertions.assertThat(bookService.findBooksByAuthorAndTitle(author, "title1"))
                .hasSize(2);
    }
    @Test
    public void findBooksByGenreTest() {
        List<Book> local_books = new ArrayList<>();
        local_books.add(Book.builder().title("title1").genre("comedy").build());
        local_books.add(Book.builder().title("title2").genre("comedy").build());
        Author author = Author.builder().lastName("pushkin").build();

        Mockito.doReturn( local_books)
                .when(bookRepository).findBookByGenre(
                        "comedy");
        Assertions.assertThat(bookService.findBooksByGenre("comedy")).hasSize(2);
    }

    @Test
    public void findBooksByAuthorTest() {
        List<Book> local_books = new ArrayList<>();
        local_books.add(Book.builder().title("title1").genre("comedy").build());
        local_books.add(Book.builder().title("title2").genre("comedy").build());
        Author pushkin = Author.builder().lastName("pushkin").build();

        Mockito.doReturn( local_books)
                .when(bookRepository).findBookByAuthor(pushkin);

        Assertions.assertThat(bookService.findBooksByAuthor(pushkin)).hasSize(2);
    }
}
