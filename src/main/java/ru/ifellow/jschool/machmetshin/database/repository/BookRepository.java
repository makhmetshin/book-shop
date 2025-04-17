package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;

import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Set<Book> findBookByGenreAndAuthor(String genre, Author author);
    Set<Book> findBookByGenre(String genre);
    Set<Book> findBookByAuthor(Author author);
    Set<Book> findBookByAuthorAndTitleContainingIgnoreCase(Author author, String title);


}