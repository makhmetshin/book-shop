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

@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
@WebAppConfiguration
public class ShopRepositoryTest {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private BillRepository billRepository;

    @Test
    public void findAllTest() {
        Assertions.assertThat(shopRepository.findAll()).hasSize(10);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(shopRepository.findById(1).get().getCity()).isEqualTo("Moscow");
        Assertions.assertThat(shopRepository.findById(5).get().getCity()).isEqualTo("Yekaterinburg");
        Assertions.assertThat(shopRepository.findById(10).get().getCity()).isEqualTo("Perm");
    }

    @Test
    public void findBySaveTest() {
        shopRepository.save(new Shop());
        Assertions.assertThat(shopRepository.findAll()).hasSize(11);
    }

    @Test
    @Transactional
    public void deleteTest() {
        Shop shop = shopRepository.findById(1).get();
        shopRepository.delete(shop);
        Assertions.assertThat(shopRepository.findAll()).hasSize(9);
    }
}
