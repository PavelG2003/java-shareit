package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class User {
    Long id;
    @NotBlank
    String name;
    @NotBlank
    @Email
    String email;
}
