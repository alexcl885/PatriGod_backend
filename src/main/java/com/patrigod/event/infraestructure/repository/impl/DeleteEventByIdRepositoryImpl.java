package com.patrigod.event.infraestructure.repository.impl;

import com.patrigod.event.application.mapper.EventMapper;
import com.patrigod.event.domain.repository.DeleteEventByIdRepository;
import com.patrigod.event.infraestructure.repository.jpa.EventRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeleteEventByIdRepositoryImpl implements DeleteEventByIdRepository {

    private final EventMapper eventMapper;

    private final EventRepositoryJpa eventRepositoryJpa;

    @Override
    public void deleteEventById(Long id) {
        eventRepositoryJpa.deleteById(id);
    }
}
