package org.example.dao;

import org.example.database.DataSource;
import org.example.entity.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderDaoTest {

    static DataSource dataSource = DataSource.getInstance();

    OrderDao dao = OrderDao.getInstance();

    @Test
    public void saveAndFindByIdTest() {
        dao.save(Order.builder().orderId(1).customerFio("ivan").build());
        dao.save(Order.builder().orderId(2).customerFio("petr").build());
        dao.save(Order.builder().orderId(3).customerFio("marat").build());

        assertThat(dao.findById(1).getCustomerFio()).isEqualTo("ivan");
        assertThat(dao.findById(2).getCustomerFio()).isEqualTo("petr");
        assertThat(dao.findById(3).getCustomerFio()).isEqualTo("marat");

    }



}
