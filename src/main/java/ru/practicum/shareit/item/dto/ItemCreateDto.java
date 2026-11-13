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
    @NotBlank
    @Size(max = 200)
    String description;
    @NotNull
    Boolean available;
    Long requestId;
}
