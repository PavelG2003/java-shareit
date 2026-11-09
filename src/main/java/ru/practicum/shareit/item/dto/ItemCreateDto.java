package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemCreateDto {
    @NotBlank
    @Size(max = 50)
    String name;
    @Size(max = 200)
    @NotNull
    String description;
    @NotNull
    Boolean available;
    Long requestId;
}
