package ru.practicum.shareit.item.dto;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getOwner().getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getRequest() != null ? item.getRequest().getId() : null
        );
    }

    public static ItemShortDto toItemShortDto(Item item) {
        return new ItemShortDto(
                item.getName(),
                item.getDescription()
        );
    }

    public static Item updateItem(Item item, ItemUpdateDto updateItem) {
        if (updateItem.getDescription() != null) {
            item.setDescription(updateItem.getDescription());
        }
        if (updateItem.getName() != null) {
            item.setName(updateItem.getName());
        }
        if (updateItem.getAvailable() != item.getAvailable()) {
            item.setAvailable(updateItem.getAvailable());
        }
        return item;
    }

    public static Item toItemFromCreateDto(ItemCreateDto requestItem, User user) {
        Item item = new Item();
        item.setOwner(user);
        item.setName(requestItem.getName());
        item.setDescription(requestItem.getDescription());
        item.setAvailable(requestItem.getAvailable());
        return  item;
    }
}
