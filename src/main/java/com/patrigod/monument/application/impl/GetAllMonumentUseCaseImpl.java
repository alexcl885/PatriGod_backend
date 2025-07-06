package com.patrigod.monument.application.impl;

import com.patrigod.monument.application.GetAllMonumentUseCase;
import com.patrigod.monument.domain.entity.Monument;
import com.patrigod.monument.domain.repository.GetAllMonumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllMonumentUseCaseImpl implements GetAllMonumentUseCase {

    private final GetAllMonumentRepository getAllMonumentRepository;

    @Override
    public List<Monument> getAllMonument() {
        return getAllMonumentRepository.getAllMonument();
    }
}
