package ru.ifellow.jschool.machmetshin.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifellow.jschool.machmetshin.entity.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
