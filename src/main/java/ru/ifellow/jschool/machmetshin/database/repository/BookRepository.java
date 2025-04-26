package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;

import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBookByGenreAndAuthor(String genre, Author author);
    List<Book> findBookByGenre(String genre);
    List<Book> findBookByAuthor(Author author);
    List<Book> findBookByAuthorAndTitleContainingIgnoreCase(Author author, String title);

}