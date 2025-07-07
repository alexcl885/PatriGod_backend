package com.patrigod.event.infraestructure.repository.impl;

import com.patrigod.event.application.mapper.EventMapper;
import com.patrigod.event.domain.entity.Event;
import com.patrigod.event.domain.repository.CreateEventRepository;
import com.patrigod.event.infraestructure.repository.jpa.EventRepositoryJpa;
import com.patrigod.event.infraestructure.repository.jpa.entity.EventJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreateEventRepositoryImpl implements CreateEventRepository {

    private final EventMapper eventMapper;

    private final EventRepositoryJpa eventRepositoryJpa;

    @Override
    public Event createEvent(Event event) {
        EventJpa eventJpa = eventMapper.toEntity(event);
        EventJpa createdEvent = eventRepositoryJpa.save(eventJpa);
        return eventMapper.toModel(createdEvent);
    }
}
