package com.patrigod.event.infraestructure.repository.impl;

import com.patrigod.event.application.mapper.EventMapper;
import com.patrigod.event.domain.entity.Event;
import com.patrigod.event.domain.repository.GetEventByIdRepository;
import com.patrigod.event.infraestructure.repository.jpa.EventRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GetEventByIdRepositoryImpl implements GetEventByIdRepository {

    private final EventMapper eventMapper;

    private final EventRepositoryJpa eventRepositoryJpa;

    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepositoryJpa.findById(id)
                .map(eventMapper::toModel);
    }
}
