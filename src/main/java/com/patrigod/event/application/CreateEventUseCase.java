package com.patrigod.event.application;

import com.patrigod.event.domain.entity.Event;

public interface CreateEventUseCase {
    Event createEvent(Event event);
}
