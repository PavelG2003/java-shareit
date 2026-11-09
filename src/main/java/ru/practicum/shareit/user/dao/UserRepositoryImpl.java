package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> usersRepository = new HashMap<>();
    private long idCounter = 0;

    @Override
    public Collection<User> findAll() {
        return usersRepository.values();
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(usersRepository.get(userId));
    }

    @Override
    public User create(User user) {
        Long userId = generatedId();
        user.setId(userId);
        usersRepository.put(userId, user);
        return user;
    }

    @Override
    public void delete(Long userId) {
        usersRepository.remove(userId);
    }

    @Override
    public void update(User user) {
        Long id = user.getId();
        usersRepository.put(id, user);
    }

    @Override
    public boolean findByEmail(String email) {
        return usersRepository.values().stream()
                .anyMatch(user -> Objects.equals(user.getEmail(), email));
    }

    private long generatedId() {
        return ++idCounter;
    }
}
