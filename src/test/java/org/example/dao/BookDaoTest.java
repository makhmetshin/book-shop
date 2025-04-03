package org.example.dao;


import org.example.database.DataSource;
import org.example.entity.Book;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BookDaoTest  {

    static DataSource dataSource = DataSource.getInstance();

    BookDao dao = BookDao.getInstance();


    @Test
    public void findAllTest() {
        assertTrue(dao.findAll().size() == 10);

    }

    @Test
    public void findByIdTest() {
        assertTrue(dao.findById(1).get().getTitle().equals("Book One"));
        assertTrue(dao.findById(5).get().getTitle().equals("Book Five"));
        assertTrue(dao.findById(10).get().getTitle().equals("Book Ten"));
    }

    @Test
    public void deleteTest() {

         boolean result = dao.delete(1);
         assertThat(dao.findAll().size()).isEqualTo(9);
         assertThat(result).isTrue();
    }

    @Test
    public void saveTest() {

        dao.save(Book.builder().build());

        assertThat(dao.findAll().size()).isEqualTo(11) ;

    }

    @Test
    public void findBooksByGenreAndAuthorTest() {
        String genre = "Cookbook";
        String author = "Author Three";

        assertThat(dao.findBooksByAuthor(author).size()).isEqualTo(3);
        assertThat(dao.findBooksByGenre(genre).size()).isEqualTo(2);
        assertThat(dao.findBooksByGenreAndAuthor(genre, author).size()).isEqualTo(0);

        assertThat(dao.findBooksByGenreAndAuthor(genre, author).size()).isEqualTo(0);
    }

    @Test
    public void findBooksByAuthorAndTitleTest() {
        assertThat(dao.findBooksByAuthorAndTitle("Author One", "Book One").size()).isEqualTo(1);
        assertThat(dao.findBooksByAuthorAndTitle("Author o", "One").size()).isEqualTo(1);
        assertThat(dao.findBooksByAuthorAndTitle("Author", "boo").size()).isEqualTo(10);
    }


}
