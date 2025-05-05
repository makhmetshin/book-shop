package ru.ifellow.jschool.machmetshin.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.config.ApplicationConfiguration;
import ru.ifellow.jschool.machmetshin.config.jpa.DataInitializer;
import ru.ifellow.jschool.machmetshin.entity.user.User;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class, DataInitializer.class})
@Transactional
@Rollback
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    public void findAllTest() {
        Assertions.assertThat(userRepository.findAll()).hasSize(10);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertThat(userRepository.findById(1).get().getName()).isEqualTo("Alexey");
        Assertions.assertThat(userRepository.findById(5).get().getName()).isEqualTo("Anastasia");
        Assertions.assertThat(userRepository.findById(10).get().getName()).isEqualTo("Maxim");
    }

    @Test
    public void findBySaveTest() {
        userRepository.save(new User());
        Assertions.assertThat(userRepository.findAll()).hasSize(11);
    }

    @Test
    public void deleteTest() {
        User user = userRepository.findById(1).get();
        userRepository.delete(user);
        Assertions.assertThat(userRepository.findAll()).hasSize(9);
    }
}
