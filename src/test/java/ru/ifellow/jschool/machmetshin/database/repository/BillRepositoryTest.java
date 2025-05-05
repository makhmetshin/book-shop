package ru.ifellow.jschool.machmetshin.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.config.ApplicationConfiguration;
import ru.ifellow.jschool.machmetshin.config.jpa.DataInitializer;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;


@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
public class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;


    @Test
    public void findAllTest() {
        Assertions.assertThat(billRepository.findAll()).hasSize(10);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(billRepository.findById(1).get().getId()).isEqualTo(1);
        Assertions.assertThat(billRepository.findById(5).get().getId()).isEqualTo(5);
        Assertions.assertThat(billRepository.findById(10).get().getId()).isEqualTo(10);
    }
    @Test
    public void findBySaveTest() {
        billRepository.save(new Bill());
        Assertions.assertThat(billRepository.findAll()).hasSize(11);
    }

    @Test
    public void deleteTest() {
        Bill bill = billRepository.findById(1).get();
        billRepository.delete(bill);
        Assertions.assertThat(billRepository.findAll()).hasSize(9);
    }



}