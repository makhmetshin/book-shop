package ru.ifellow.jschool.machmetshin.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ifellow.jschool.machmetshin.database.repository.UserRepository;
import ru.ifellow.jschool.machmetshin.dto.user.CreateUserDto;
import ru.ifellow.jschool.machmetshin.dto.user.UserAccountDto;
import ru.ifellow.jschool.machmetshin.dto.user.UserOrdersDto;
import ru.ifellow.jschool.machmetshin.entity.user.Role;
import ru.ifellow.jschool.machmetshin.entity.user.User;
import ru.ifellow.jschool.machmetshin.service.interfaces.Finder;
import ru.ifellow.jschool.machmetshin.validator.EntityExistsValidator;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService implements Finder<Integer, User>, UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final EntityExistsValidator entityExistsValidator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " is not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(user.getRole())
        );
    }

    public User findByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " is not found"));
    }

    public UserOrdersDto findByUserIdWithOrders(Integer id) {
        User user = entityExistsValidator.validate(userRepository.findById(id), id, User.class);

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
        User user = entityExistsValidator.validate(userRepository.findById(id), id, User.class);

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
        User user = entityExistsValidator.validate(userRepository.findById(id), id, User.class);
        user.setUsername(userAccountDto.getUsername());
        user.setName(userAccountDto.getName());
        user.setSurname(userAccountDto.getSurname());
        user.setLastName(userAccountDto.getLastName());
        user.setEmail(userAccountDto.getEmail());
        userRepository.save(user);
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
                .role(Role.valueOf(createUserDto.getRole()))
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void delete(Integer id) {
        User user = entityExistsValidator.validate(userRepository.findById(id), id, User.class);
        userRepository.delete(user);
    }
}
