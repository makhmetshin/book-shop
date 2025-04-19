package ru.ifellow.jschool.machmetshin.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.entity.order.Bill;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.ShopBook;
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;

import java.util.List;

@DataJpaTest
public class WarehouseRepositoryTest {
    @Autowired
    private WarehouseRepository warehouseRepository;


    @Test
    public void findAllTest() {
        Assertions.assertThat(warehouseRepository.findAll()).hasSize(10);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(warehouseRepository.findById(1).get().getCity()).isEqualTo("Moscow");
        Assertions.assertThat(warehouseRepository.findById(5).get().getCity()).isEqualTo("Yekaterinburg");
        Assertions.assertThat(warehouseRepository.findById(10).get().getCity()).isEqualTo("Perm");
    }

    @Test
    public void findBySaveTest() {
        warehouseRepository.save(new Warehouse());
        Assertions.assertThat(warehouseRepository.findAll()).hasSize(11);
    }

    @Test
    @Transactional
    public void deleteTest() {
        Warehouse warehouse = warehouseRepository.findById(1).get();
        warehouseRepository.delete(warehouse);
        Assertions.assertThat(warehouseRepository.findAll()).hasSize(9);

    }
}
