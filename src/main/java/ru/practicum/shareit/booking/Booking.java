package ru.practicum.shareit.booking;

import lombok.Data;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.Instant;

/**
 * TODO Sprint add-bookings.
 */
@Data
public class Booking {
    Long id;
    Instant start;
    Instant end;
    Item item;
    User booker;
    BookingStatus status;
}
