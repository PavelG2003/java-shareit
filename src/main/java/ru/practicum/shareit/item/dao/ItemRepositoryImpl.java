package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.*;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Long, Item> items = new HashMap<>();
    private long idCounter = 0;

    @Override
    public Item create(Item item) {
        Long id = generateId();
        item.setId(id);
        items.put(id, item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        return Optional.ofNullable(items.get(itemId));
    }

    @Override
    public void update(Item item) {
        Long id = item.getId();
        items.put(id, item);
    }

    @Override
    public List<Item> findAllByUserId(Long userId) {
        return items.values().stream()
                .filter(item -> Objects.equals(item.getOwner().getId(), userId))
                .toList();
    }

    @Override
    public List<Item> searchAvailable(String text) {
        String lowerText = text.toLowerCase();
        return items.values().stream()
                .filter(item -> Boolean.TRUE.equals(item.getAvailable()))
                .filter(item -> containsText(item.getDescription(), lowerText)
                || containsText(item.getName(), lowerText))
                .toList();

    }

    private boolean containsText(String itemValue, String text) {
        return (itemValue != null) && itemValue.toLowerCase().contains(text);
    }

    private long generateId() {
        return ++idCounter;
    }
}
