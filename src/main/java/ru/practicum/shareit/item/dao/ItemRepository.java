package ru.practicum.shareit.item.dao;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item create(Item item);

    Optional<Item> findById(Long itemId);

    void update(Item item);

    List<Item> findAllByUserId(Long userId);

    List<Item> searchAvailable(String text);
}
