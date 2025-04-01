package org.example.dao;


import org.example.database.DataSource;
import org.example.entity.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BookDao  {

    private DataSource dataSource = DataSource.getInstance();

    private final static BookDao INSTANCE = new BookDao();

    private BookDao () {}

    public static BookDao getInstance() {
        return new BookDao();
//        return INSTANCE;
    }

    Set<Book> books = dataSource.getBooks();


    public Set<Book> findAll() {
        return books;
    }


    public Optional<Book> findById(Integer id) {

        for (Book book : books)
            if(book.getId().equals(id)) return Optional.of(book);

        return Optional.empty();
    }


    public boolean delete(Integer id) {
        return books.removeIf(book -> book.getId().equals(id));
    }


    public void save(Book book) {
        books.add(book);
    }

    public Set<Book> findBooksByGenreAndAuthor(String genre, String author) {
        return books.stream()
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
        return books.stream()
                .filter(book -> (author == null || book.getAuthor().toLowerCase().contains(author.toLowerCase())) &&
                        (title == null || book.getTitle().toLowerCase().contains(title.toLowerCase())))
                .collect(Collectors.toSet());
    }

}
