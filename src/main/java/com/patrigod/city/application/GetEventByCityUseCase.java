package com.patrigod.city.application;

import com.patrigod.event.domain.entity.Event;

import java.util.List;

public interface GetEventByCityUseCase {
    List<Event> getEventOfCity(Long idCity);
}
