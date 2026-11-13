package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UpdateUserDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUser(Long userId);

    UserDto createUser(User user);

    void deleteUser(Long userId);

    UserDto updateUser(Long userId, UpdateUserDto requestUser);
}
