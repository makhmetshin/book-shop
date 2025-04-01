package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Warehouse;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class WarehouseDaoTest {
    static DataSource dataSource = DataSource.getInstance();

    WarehouseDao dao = WarehouseDao.getInstance();

    @Test
    public void findByIdTest() {
        assertThat(dao.findById(1).get().getAddress()).isEqualTo("1 Warehouse St");
        assertThat(dao.findById(5).get().getCity()).isEqualTo("City 5");
        assertThat(dao.findById(10).get().getCity()).isEqualTo("City 10");
    }






}
