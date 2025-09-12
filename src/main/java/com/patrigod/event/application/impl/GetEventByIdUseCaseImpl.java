package com.patrigod.event.application.impl;

import com.patrigod.event.application.GetEventByIdUseCase;
import com.patrigod.event.domain.entity.Event;
import com.patrigod.event.domain.repository.GetEventByIdRepository;
import com.patrigod.exception.messages.ErrorMessages;
import com.patrigod.exception.type.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetEventByIdUseCaseImpl implements GetEventByIdUseCase {

    private final GetEventByIdRepository getEventByIdRepository;

    @Override
    public Event getEventById(Long id) {
        return getEventByIdRepository.getEventById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.EVENT_NOT_FOUND_BY_ID_EXCEPTION + id));
    }
}
