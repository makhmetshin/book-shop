package ru.ifellow.jschool.machmetshin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ifellow.jschool.machmetshin.database.repository.UserRepository;
import ru.ifellow.jschool.machmetshin.entity.storage.Shop;
import ru.ifellow.jschool.machmetshin.entity.storage.Storage;
import ru.ifellow.jschool.machmetshin.entity.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private UserService userService;

    private List<User> users = new ArrayList<>();

    @Test
    public void findAllTest() {
        for (int i = 0; i < 11; i++)
            users.add(new User());

        Mockito.doReturn(users).when(userRepository).findAll();
        Assertions.assertThat(userService.findAll()).hasSize(11);
    }

    @Test
    public void findByIdTest() {
        User user = new User();
        user.setName("name");

        Mockito.doReturn(Optional.ofNullable(user) )
                .when(userRepository).findById(1);

        Assertions.assertThat(userService.findById(1).get().getName())
                .isEqualTo("name");
    }

}
