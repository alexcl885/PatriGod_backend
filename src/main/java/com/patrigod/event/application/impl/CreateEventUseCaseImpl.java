package com.patrigod.event.application.impl;

import com.patrigod.event.application.CreateEventUseCase;
import com.patrigod.event.domain.entity.Event;
import com.patrigod.event.domain.repository.CreateEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final CreateEventRepository createEventRepository;

    @Override
    public Event createEvent(Event event) {
        return createEventRepository.createEvent(event);
    }
}
