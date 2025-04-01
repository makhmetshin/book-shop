package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Shop;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopDaoTest {
    static DataSource dataSource = DataSource.getInstance();

    ShopDao dao = ShopDao.getInstance();

    @Test
    public void findByIdTest() {
        assertThat(dao.findById(1).get().getAddress()).isEqualTo("123 Main St");
        assertThat(dao.findById(5).get().getCity()).isEqualTo("Phoenix");
        assertThat(dao.findById(10).get().getCity()).isEqualTo("San Jose");
    }

}
