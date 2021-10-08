package ru.ershov.pro_education.repository;

import org.springframework.stereotype.Repository;
import ru.ershov.pro_education.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByUsername(String username);
}
