package com.patrigod.monument.application.impl;

import com.patrigod.monument.application.GetMonumentByIdUseCase;
import com.patrigod.monument.domain.entity.Monument;
import com.patrigod.monument.domain.repository.GetMonumentByIdRepository;
import com.patrigod.exception.messages.ErrorMessages;
import com.patrigod.exception.type.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMonumentByIdUseCaseImpl implements GetMonumentByIdUseCase {

    private final GetMonumentByIdRepository getMonumentByIdRepository;

    @Override
    public Monument getMonumentById(Long id) {
        return getMonumentByIdRepository.getMonumentById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.MONUMENT_NOT_FOUND_BY_ID_EXCEPTION + id));
    }
}
