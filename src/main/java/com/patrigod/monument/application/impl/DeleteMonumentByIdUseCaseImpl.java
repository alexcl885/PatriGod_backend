package com.patrigod.monument.application.impl;

import com.patrigod.monument.application.DeleteMonumentByIdUseCase;
import com.patrigod.monument.domain.repository.DeleteMonumentByIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMonumentByIdUseCaseImpl implements DeleteMonumentByIdUseCase {

    private final DeleteMonumentByIdRepository deleteMonumentByIdRepository;

    @Override
    public void deleteMonumentById(Long id) {

        deleteMonumentByIdRepository.deleteMonumentById(id);
    }
}
