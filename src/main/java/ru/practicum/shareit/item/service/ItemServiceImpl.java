package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.ForbiddenException;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.ItemRequestRepository;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ItemRequestRepository itemRequestRepository;

    @Override
    public ItemDto createItem(ItemCreateDto requestItem, Long userId) {
        User user = findUserById(userId);
        Item item = ItemMapper.toItemFromCreateDto(requestItem, user);
        if (requestItem.getRequestId() != null) {
            item.setRequest(itemRequestRepository.findById(requestItem.getRequestId())
                    .orElseThrow(() -> new NotFoundException(
                            "Запрос с id: " + requestItem.getRequestId() + " не найден"
                    )));
        }
        Item created = itemRepository.create(item);
        return ItemMapper.toItemDto(created);
    }

    @Override
    public ItemDto updateItem(Long userId, Long itemId, ItemUpdateDto requestItem) {
        User user = findUserById(userId);
        Item item = findItemById(itemId);
        if (item.getOwner() != user) {
            throw new ForbiddenException("Доступ на редактирование вещи запрещён");
        }
        Item updated = ItemMapper.updateItem(item, requestItem);
        itemRepository.update(updated);
        return ItemMapper.toItemDto(updated);
    }

    @Override
    public ItemDto getItem(Long userId, Long itemId) {
        Item item = findItemById(itemId);
        return ItemMapper.toItemDto(item);
    }

    @Override
    public List<ItemShortDto> getAllUserItems(Long userId) {
        List<Item> userItems = itemRepository.findAllByUserId(userId);
        return userItems.stream()
                .map(ItemMapper::toItemShortDto)
                .toList();
    }

    @Override
    public List<ItemDto> searchItems(long userId, String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }
        return itemRepository.searchAvailable(text).stream()
                .map(ItemMapper::toItemDto)
                .toList();
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id: " + userId + " не найден"));
    }

    private Item findItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item с id: " + itemId + " не найден"));
    }
}
