package ru.practicum.shareit.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.practicum.shareit.user.model.User;

import java.time.Instant;

/**
 * TODO Sprint add-item-requests.
 */
@Data
public class ItemRequest {
    Long id;
    @Size(max = 200)
    String description;
    User requestor;
    Instant created;
}
