package ru.ifellow.jschool.machmetshin.dao;

import ru.ifellow.jschool.machmetshin.database.DataSource;
import ru.ifellow.jschool.machmetshin.entity.order.Order;
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

        assertThat(dao.findById(1).get().getCustomerFio()).isEqualTo("ivan");
        assertThat(dao.findById(2).get().getCustomerFio()).isEqualTo("petr");
        assertThat(dao.findById(3).get().getCustomerFio()).isEqualTo("marat");

    }



}
