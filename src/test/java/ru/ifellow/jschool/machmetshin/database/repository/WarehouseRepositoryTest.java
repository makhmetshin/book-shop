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
import ru.ifellow.jschool.machmetshin.entity.storage.Warehouse;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
@WebAppConfiguration
public class WarehouseRepositoryTest {
    @Autowired
    private WarehouseRepository warehouseRepository;


    @Test
    public void findAllTest() {
        Assertions.assertThat(warehouseRepository.findAll()).hasSize(10);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(warehouseRepository.findById(11).get().getCity()).isEqualTo("Moscow");
        Assertions.assertThat(warehouseRepository.findById(15).get().getCity()).isEqualTo("Yekaterinburg");
        Assertions.assertThat(warehouseRepository.findById(20).get().getCity()).isEqualTo("Perm");
    }

    @Test
    public void findBySaveTest() {
        warehouseRepository.save(new Warehouse());
        Assertions.assertThat(warehouseRepository.findAll()).hasSize(11);
    }

    @Test
    @Transactional
    public void deleteTest() {
        Warehouse warehouse = warehouseRepository.findById(11).get();
        warehouseRepository.delete(warehouse);
        Assertions.assertThat(warehouseRepository.findAll()).hasSize(9);

    }
}
