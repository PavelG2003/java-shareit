package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemShortDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;
import ru.practicum.shareit.item.service.ItemServiceImpl;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemServiceImpl itemService;
    private static final String USER_ID_HEADER = "X-Sharer-User-Id";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(
            @Valid @RequestBody ItemCreateDto requestItem,
            @RequestHeader(USER_ID_HEADER) Long userId
    ) {
        return itemService.createItem(requestItem, userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(
            @RequestHeader(USER_ID_HEADER) Long userId,
            @PathVariable Long itemId,
            @Valid @RequestBody ItemUpdateDto requestItem
    ) {
        return itemService.updateItem(userId, itemId, requestItem);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItem(
            @RequestHeader(USER_ID_HEADER) Long userId,
            @PathVariable Long itemId
    ) {
        return itemService.getItem(userId, itemId);
    }

    @GetMapping
    public List<ItemShortDto> getAllUserItems(@RequestHeader(USER_ID_HEADER) Long userId) {
        return itemService.getAllUserItems(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItems(
            @RequestHeader(USER_ID_HEADER) Long userId,
            @RequestParam String text
    ) {
        return itemService.searchItems(userId, text);
    }

}
