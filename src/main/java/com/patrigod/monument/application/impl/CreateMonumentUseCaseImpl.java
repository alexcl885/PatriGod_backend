package com.patrigod.monument.application.impl;

import com.patrigod.monument.application.CreateMonumentUseCase;
import com.patrigod.monument.domain.entity.Monument;
import com.patrigod.monument.domain.repository.CreateMonumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMonumentUseCaseImpl implements CreateMonumentUseCase {

    private final CreateMonumentRepository createMonumentRepository;

    @Override
    public Monument createMonument(Monument monument, Long idCity) {
        return createMonumentRepository.createMonument(monument, idCity);
    }
}
