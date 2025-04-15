package ru.ifellow.jschool.machmetshin.dao;

import org.assertj.core.api.Assertions;
import ru.ifellow.jschool.machmetshin.database.DataSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopDaoTest {
    static DataSource dataSource = DataSource.getInstance();

    ShopDao dao = ShopDao.getInstance();

    @Test
    public void findByIdTest() {
        Assertions.assertThat(dao.findById(1).get().getAddress()).isEqualTo("123 Main St");
        Assertions.assertThat(dao.findById(5).get().getCity()).isEqualTo("Phoenix");
        Assertions.assertThat(dao.findById(10).get().getCity()).isEqualTo("San Jose");
    }

}
