package org.example.dao;


import org.example.database.DataSource;
import org.example.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class BookDao  {

    private DataSource dataSource;
    private Set<Book> books;

    public BookDao() {}

    public static BookDao getInstance() {
        return null;
    }

    @Autowired
    public BookDao (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Set<Book> findAll() {
        return dataSource.getBooks();
    }



    public Optional<Book> findById(Integer id) {
        books = dataSource.getBooks();
        for (Book book : books)
            if(book.getId().equals(id)) return Optional.of(book);

        return Optional.empty();
    }


    public boolean delete(Integer id) {
        return dataSource.getBooks().removeIf(book -> book.getId().equals(id));
    }


    public void save(Book book) {
        dataSource.getBooks().add(book);
    }

    public Set<Book> findBooksByGenreAndAuthor(String genre, String author) {
        return dataSource.getBooks().stream()
                .filter(book -> (genre == null || book.getGenre().equalsIgnoreCase(genre)) &&
                                (author == null || book.getAuthor().equalsIgnoreCase(author))
                )
                .collect(Collectors.toSet());

    }
    public Set<Book> findBooksByGenre(String genre) {
        return findBooksByGenreAndAuthor(genre, null);
    }
    public Set<Book> findBooksByAuthor(String author) {
        return findBooksByGenreAndAuthor(null, author);
    }

    public Set<Book> findBooksByAuthorAndTitle(String author, String title) {
        return dataSource.getBooks().stream()
                .filter(book -> (author == null || book.getAuthor().toLowerCase().contains(author.toLowerCase())) &&
                        (title == null || book.getTitle().toLowerCase().contains(title.toLowerCase())))
                .collect(Collectors.toSet());
    }

}
