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
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
@WebAppConfiguration
public class StorageRepositoryTest {

    @Autowired
    private StorageRepository storageRepository;


    @Test
    public void findAllTest() {
        Assertions.assertThat(storageRepository.findAll()).hasSize(20);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(storageRepository.findById(1).get().getCity()).isEqualTo("Moscow");
        Assertions.assertThat(storageRepository.findById(10).get().getCity()).isEqualTo("Perm");
        Assertions.assertThat(storageRepository.findById(20).get().getCity()).isEqualTo("Perm");
    }

    @Test
    public void findBySaveTest() {
        Storage storage = new Shop();
        storageRepository.save(storage);
        Assertions.assertThat(storageRepository.findAll()).hasSize(21);
    }

    @Test
    public void deleteTest() {
        Storage storage = storageRepository.findById(1).get();
        storageRepository.delete(storage);
        Assertions.assertThat(storageRepository.findAll()).hasSize(19);
    }
}
