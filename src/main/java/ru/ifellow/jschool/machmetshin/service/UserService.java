package ru.ifellow.jschool.machmetshin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ifellow.jschool.machmetshin.database.repository.UserRepository;
import ru.ifellow.jschool.machmetshin.entity.user.User;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public Optional<User> findById(Integer id) {
       return userRepository.findById(id);
    }
}
