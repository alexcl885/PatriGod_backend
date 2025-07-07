package com.patrigod.event.domain.repository;

import com.patrigod.event.domain.entity.Event;

public interface CreateEventRepository {
    Event createEvent(Event event);
}
