package ru.ifellow.jschool.machmetshin.dao;


import ru.ifellow.jschool.machmetshin.database.DataSource;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import org.junit.jupiter.api.*;

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
        Assertions.assertTrue(dao.findById(1).get().getTitle().equals("Book One"));
        Assertions.assertTrue(dao.findById(5).get().getTitle().equals("Book Five"));
        Assertions.assertTrue(dao.findById(10).get().getTitle().equals("Book Ten"));
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
