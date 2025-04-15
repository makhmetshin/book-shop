package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.dao.BookDao;
import ru.ifellow.jschool.machmetshin.entity.Book;

import java.util.Optional;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class BookService {
    private BookDao bookDao;

    public Set<Book> findAll() {
        return bookDao.findAll();
    }

    public Set<Book> findBooksByGenreAndAuthor(String genre, String author) {
        return bookDao.findBooksByGenreAndAuthor(genre, author);
    }

    public Set<Book> findBooksByAuthorAndTitle(String author, String title) {
        return bookDao.findBooksByAuthorAndTitle(author, title);
    }
    public Set<Book> findBooksByGenre(String genre) {
        return bookDao.findBooksByGenre(genre);
    }

    public Set<Book> findBooksByAuthor(String author) {
        return bookDao.findBooksByAuthor(author);
    }

    public Optional<Book> findById(Integer bookId) {
        return bookDao.findById(bookId);
    }
}
