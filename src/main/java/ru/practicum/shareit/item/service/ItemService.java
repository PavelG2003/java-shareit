package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemShortDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;

import java.util.List;

public interface ItemService {

    ItemDto createItem(ItemCreateDto requestItem, Long userId);

    ItemDto updateItem(Long userId, Long itemId, ItemUpdateDto requestItem);

    ItemDto getItem(Long userId, Long itemId);

    List<ItemShortDto> getAllUserItems(Long userId);

    List<ItemDto> searchItems(long userId, String text);

}
