package ru.ifellow.jschool.machmetshin.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.UserRepository;
import ru.ifellow.jschool.machmetshin.dto.user.CreateUserDto;
import ru.ifellow.jschool.machmetshin.dto.user.UserAccountDto;
import ru.ifellow.jschool.machmetshin.dto.user.UserOrdersDto;
import ru.ifellow.jschool.machmetshin.entity.user.User;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;
import ru.ifellow.jschool.machmetshin.validator.EntityFoundByIdRepositoryValidator;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService implements Findable<Integer, User> {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final EntityFoundByIdRepositoryValidator entityFoundByIdRepositoryValidator;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserOrdersDto findByUserIdWithOrders(Integer id) {
        User user = entityFoundByIdRepositoryValidator.validate(userRepository, id, User.class);

        List<Integer> orderIds = user.getOrders().stream()
                .map(order -> order.getId())
                .distinct()
                .toList();

        UserOrdersDto userOrdersDto = UserOrdersDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .lastName(user.getLastName())
                .ordersIds(orderIds)
                .build();
        return userOrdersDto;
    }

    public UserAccountDto findByUserIdAccountDetails(Integer id) {
        User user = entityFoundByIdRepositoryValidator.validate(userRepository, id, User.class);

        return UserAccountDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
    @Transactional
    public void updateAccountDetails(Integer id, UserAccountDto userAccountDto) {
        System.out.println("update started");
        User user = entityFoundByIdRepositoryValidator.validate(userRepository, id, User.class);
        System.out.println(user);
        user.setUsername(userAccountDto.getUsername());
        user.setName(userAccountDto.getName());
        user.setSurname(userAccountDto.getSurname());
        user.setLastName(userAccountDto.getLastName());
        user.setEmail(userAccountDto.getEmail());
        System.out.println(user);
        userRepository.save(user);
        System.out.println("again from db");
        user = entityFoundByIdRepositoryValidator.validate(userRepository, id, User.class);
        System.out.println(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Transactional
    public void save(CreateUserDto createUserDto) {
        User user = User.builder()
                .username(createUserDto.getUsername())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .name(createUserDto.getName())
                .surname(createUserDto.getSurname())
                .lastName(createUserDto.getLastName())
                .email(createUserDto.getEmail())
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void delete(Integer id) {
        User user = entityFoundByIdRepositoryValidator.validate(userRepository, id, User.class);
        userRepository.delete(user);
    }
}
