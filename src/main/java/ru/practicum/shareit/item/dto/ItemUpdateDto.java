package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemUpdateDto {
    @Size(max = 50)
    String name;
    @Size(max = 200)
    String description;
    Boolean available;
}
