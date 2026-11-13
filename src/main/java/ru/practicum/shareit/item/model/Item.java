package ru.practicum.shareit.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.model.User;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class Item {
    Long id;
    @NotNull
    User owner;
    @NotBlank
    @Size(max = 50)
    String name;
    @Size(max = 200)
    String description;
    Boolean available;
    ItemRequest request;
}
