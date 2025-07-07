package com.patrigod.event.application;

import com.patrigod.event.domain.entity.Event;

import java.util.List;

public interface GetAllEventUseCase {
    List<Event> getAllEvent();
}
