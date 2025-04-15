package ru.ifellow.jschool.machmetshin.dao;

import ru.ifellow.jschool.machmetshin.database.DataSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopBookDaoTest {
    static DataSource dataSource = DataSource.getInstance();

    ShopBookDao dao = ShopBookDao.getInstance();


    @Test
    public void findAllTest() {
        assertThat(dao.findAll().size()).isEqualTo(100);
    }

    @Test
    public void addBookTest() {
        dao.addBook(1, 1, 100);
        assertThat(dao.findByShopIdAndBookId(1,1).get().getBookAmount())
                .isEqualTo(1100);
        assertThat(dao.findByShopIdAndBookId(10,1).get().getBookAmount())
                .isEqualTo(10000);
        assertThat(dao.findByShopIdAndBookId(5,3).get().getBookAmount())
                .isEqualTo(5000);
    }

    @Test
    public void removeBookTest() {
        dao.removeBook(1, 1, 1000);
        dao.removeBook(1, 5, 2500);
        dao.removeBook(1, 1, 0);
        assertThat(dao.findByShopIdAndBookId(1,1).get().getBookAmount())
                .isEqualTo(0);
        assertThat(dao.findByShopIdAndBookId(5,1).get().getBookAmount())
                .isEqualTo(2500);
        assertThat(dao.findByShopIdAndBookId(10,3).get().getBookAmount())
                .isEqualTo(10000);
    }

    @Test
    public void findByShopIdAndBookIdTest() {
        assertThat(dao.findByShopIdAndBookId(1,1).get().getBookAmount())
                .isEqualTo(1000);
        assertThat(dao.findByShopIdAndBookId(2,2).get().getBookAmount())
                .isEqualTo(2000);
        assertThat(dao.findByShopIdAndBookId(3,3).get().getBookAmount())
                .isEqualTo(3000);
    }





}
