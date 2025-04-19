package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.database.repository.BookRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class BookService {

    private BookRepository bookRepository; //private final

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Set<Book> findBooksByGenreAndAuthor(String genre, Author author) {
        return bookRepository.findBookByGenreAndAuthor(genre, author);
    }

    public Set<Book> findBooksByAuthorAndTitle(Author author, String title) {
        return bookRepository.findBookByAuthorAndTitleContainingIgnoreCase(author, title);
    }
    public Set<Book> findBooksByGenre(String genre) {
        return bookRepository.findBookByGenre(genre);
    }

    public Set<Book> findBooksByAuthor(Author author) {
        return bookRepository.findBookByAuthor(author);
    }

    public Optional<Book> findById(Integer bookId) {
        return bookRepository.findById(bookId);
    }
}
