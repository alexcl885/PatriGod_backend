package com.patrigod.event.domain.repository;

import com.patrigod.event.domain.entity.Event;

import java.util.Optional;

public interface GetEventByIdRepository {
    Optional<Event> getEventById(Long id);
}
