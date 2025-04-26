package ru.ifellow.jschool.machmetshin.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.ifellow.jschool.machmetshin.entity.good.book.Book;
import ru.ifellow.jschool.machmetshin.entity.storage.StorageGood;

@DataJpaTest
public class StorageGoodRepositoryTest {



    @Autowired
    private StorageGoodRepository storageGoodRepository;

    @Test
    public void findAllTest() {
        Assertions.assertThat(storageGoodRepository.findAll()).hasSize(200);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(storageGoodRepository.findById(1).get().getStorage().getId()).isEqualTo(1);
        Assertions.assertThat(storageGoodRepository.findById(100).get().getStorage().getId()).isEqualTo(10);
        Assertions.assertThat(storageGoodRepository.findById(200).get().getStorage().getId()).isEqualTo(20);
    }
    @Test
    public void findBySaveTest() {
        storageGoodRepository.save(new StorageGood());
        Assertions.assertThat(storageGoodRepository.findAll()).hasSize(201);
    }

    @Test
    public void deleteTest() {
        StorageGood storageGood = storageGoodRepository.findById(1).get();
        storageGoodRepository.delete(storageGood);
        Assertions.assertThat(storageGoodRepository.findAll()).hasSize(199);
    }


}
