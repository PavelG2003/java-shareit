package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.ForbiddenException;
import ru.practicum.shareit.exceptions.NotFoundException;
import ru.practicum.shareit.item.dao.ItemRepositoryImpl;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.ItemRequestRepository;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemRepositoryImpl itemRepository;
    private final UserRepository userRepository;
    private final ItemRequestRepository itemRequestRepository;

    @Override
    public ItemDto createItem(ItemCreateDto requestItem, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Item item = new Item();
        item.setOwner(user);
        item.setName(requestItem.getName());
        item.setDescription(requestItem.getDescription());
        item.setAvailable(requestItem.getAvailable());
        if (requestItem.getRequestId() != null) {
            item.setRequest(itemRequestRepository.findById(requestItem.getRequestId())
                    .orElseThrow(() -> new NotFoundException("Запрос не найден")));
        }
        Item created = itemRepository.create(item);
        return ItemMapper.toItemDto(created);
    }

    @Override
    public ItemDto updateItem(Long userId, Long itemId, ItemUpdateDto requestItem) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item не найден"));
        if (item.getOwner() != user) {
            throw new ForbiddenException("Доступ на редактирование вещи запрещён");
        }
        Item updated = ItemMapper.updateItem(item, requestItem);
        itemRepository.update(updated);
        return ItemMapper.toItemDto(updated);
    }

    @Override
    public ItemDto getItem(Long userId, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item не найден"));
        return ItemMapper.toItemDto(item);
    }

    @Override
    public List<ItemShortDto> getAllUserItems(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        List<Item> userItems = itemRepository.findAllByUserId(userId);
        return userItems.stream()
                .map(ItemMapper::toItemShortDto)
                .toList();
    }

    @Override
    public List<ItemDto> searchItems(long userId, String text) {
        if (text.isBlank()) {
            return List.of();
        }
        return itemRepository.searchAvailable(text).stream()
                .map(ItemMapper::toItemDto)
                .toList();
    }
}
