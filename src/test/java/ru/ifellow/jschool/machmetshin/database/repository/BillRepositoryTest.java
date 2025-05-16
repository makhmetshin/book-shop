package ru.ifellow.jschool.machmetshin.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.config.ApplicationConfiguration;
import ru.ifellow.jschool.machmetshin.config.jpa.DataInitializer;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;


@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
@WebAppConfiguration
public class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;


    @Test
    public void findAllTest() {
        Assertions.assertThat(billRepository.findAll()).hasSize(2);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(billRepository.findById(1).get().getId()).isEqualTo(1);
    }
    @Test
    public void saveTest() {
        billRepository.save(new Bill());
        Assertions.assertThat(billRepository.findAll()).hasSize(3);
    }

    @Test
    public void deleteTest() {
        Bill bill = billRepository.findById(1).get();
        bill.getOrder().setBill(null);
        billRepository.delete(bill);
        Assertions.assertThat(billRepository.findAll()).hasSize(1);
    }



}