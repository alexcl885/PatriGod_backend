package com.patrigod.event.application;

import com.patrigod.event.domain.entity.Event;

public interface GetEventByIdUseCase {
    Event getEventById(Long id);
}
