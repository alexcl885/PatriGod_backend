package com.patrigod.event.application.impl;

import com.patrigod.event.application.DeleteEventByIdUseCase;
import com.patrigod.event.application.GetEventByIdUseCase;
import com.patrigod.event.domain.repository.DeleteEventByIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteEventByIdUseCaseImpl implements DeleteEventByIdUseCase {

    private final DeleteEventByIdRepository deleteEventByIdRepository;

    private final GetEventByIdUseCase getEventByIdUseCase;

    @Override
    public void deleteEventById(Long id) {

        getEventByIdUseCase.getEventById(id);

        deleteEventByIdRepository.deleteEventById(id);
    }
}
