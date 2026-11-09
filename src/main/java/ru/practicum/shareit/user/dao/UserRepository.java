package ru.practicum.shareit.user.dao;

import ru.practicum.shareit.user.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

    Collection<User> findAll();

    Optional<User> findById(Long userId);

    User create(User user);

    void delete(Long userId);

    void update(User user);

    boolean findByEmail(String email);
}
