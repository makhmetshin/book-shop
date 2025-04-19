package ru.ifellow.jschool.machmetshin.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.ifellow.jschool.machmetshin.entity.good.book.Publisher;
import ru.ifellow.jschool.machmetshin.entity.order.Order;

@DataJpaTest
public class PublisherRepositoryTest {

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void findAllTest() {
        Assertions.assertThat(publisherRepository.findAll()).hasSize(3);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(publisherRepository.findById(1).get().getName()).isEqualTo("Penguin Books");
        Assertions.assertThat(publisherRepository.findById(2).get().getName()).isEqualTo("HarperCollins");
        Assertions.assertThat(publisherRepository.findById(3).get().getName()).isEqualTo("Vintage Books");
    }

    @Test
    public void findBySaveTest() {
        publisherRepository.save(new Publisher());
        Assertions.assertThat(publisherRepository.findAll()).hasSize(4);
    }

    @Test
    public void deleteTest() {
        Publisher publisher = publisherRepository.findById(1).get();
        publisherRepository.delete(publisher);
        Assertions.assertThat(publisherRepository.findAll()).hasSize(2);
    }
}
