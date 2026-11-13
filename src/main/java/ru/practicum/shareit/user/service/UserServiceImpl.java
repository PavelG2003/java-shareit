package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.DuplicatedEmailException;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.user.dto.UpdateUserDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepositoryImpl;

    @Override
    public List<UserDto> getAllUsers() {
        Collection<User> users = userRepositoryImpl.findAll();
        List<UserDto> usersDto = users.stream()
                .map(UserMapper::toUserDto)
                .toList();
        return usersDto;
    }

    @Override
    public UserDto getUser(Long userId) {
        if (userId != null) {
            User user = findUserById(userId);
            return UserMapper.toUserDto(user);
        } else {
            return null;
        }
    }

    @Override
    public UserDto createUser(User user) {
        if (userRepositoryImpl.findByEmail(user.getEmail())) {
            throw new DuplicatedEmailException("Пользователь с таким email уже существует");
        }
        User createdUser = userRepositoryImpl.create(user);
        return UserMapper.toUserDto(createdUser);
    }

    @Override
    public void deleteUser(Long userId) {
        if (userId != null) {
            userRepositoryImpl.delete(userId);
        }
    }

    @Override
    public UserDto updateUser(Long userId, UpdateUserDto requestUser) {
        User user = findUserById(userId);
        if (userRepositoryImpl.findByEmail(requestUser.getEmail())) {
            throw new DuplicatedEmailException("Пользователь с таким email уже существует");
        }
        User updated = UserMapper.updateUser(user, requestUser);
        userRepositoryImpl.update(user);
        return UserMapper.toUserDto(updated);
    }

    private User findUserById(Long userId) {
        return userRepositoryImpl.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id: " + userId + " не найден"));
    }
}
