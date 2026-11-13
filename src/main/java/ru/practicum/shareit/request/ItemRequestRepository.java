package ru.practicum.shareit.request;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ItemRequestRepository {
    private final Map<Long, ItemRequest> itemRequests = new HashMap<>();

    public Optional<ItemRequest> findById(Long id) {
        return Optional.ofNullable(itemRequests.get(id));
    }
}
