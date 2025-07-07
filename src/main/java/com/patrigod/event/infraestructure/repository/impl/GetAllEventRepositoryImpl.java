package com.patrigod.event.infraestructure.repository.impl;

import com.patrigod.event.application.mapper.EventMapper;
import com.patrigod.event.domain.entity.Event;
import com.patrigod.event.domain.repository.GetAllEventRepository;
import com.patrigod.event.infraestructure.repository.jpa.EventRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GetAllEventRepositoryImpl implements GetAllEventRepository {

    private final EventMapper eventMapper;

    private final EventRepositoryJpa eventRepositoryJpa;

    @Override
    public List<Event> getAllEvent() {
        return eventRepositoryJpa.findAll()
                .stream()
                .map(eventMapper::toModel)
                .toList();
    }
}
