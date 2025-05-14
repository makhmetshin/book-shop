package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.good.book.Author;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

//тут и далее - EntityGraph для избавления от n+1 проблемы, верно?
public interface BookRepository extends JpaRepository<Book, Integer> {
    @EntityGraph(attributePaths = {"author", "publisher"})
    Optional<Book> findById(Integer id);

    @EntityGraph(attributePaths = {"author", "publisher"})
    List<Book> findBookByGenreAndAuthor(String genre, Author author);

    List<Book> findBookByGenre(String genre);
    List<Book> findBookByAuthor(Author author);
    List<Book> findBookByAuthorAndTitleContainingIgnoreCase(Author author, String title);

}