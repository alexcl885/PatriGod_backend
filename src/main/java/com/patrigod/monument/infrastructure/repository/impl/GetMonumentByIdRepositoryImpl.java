package com.patrigod.monument.infrastructure.repository.impl;

import com.patrigod.monument.application.mapper.MonumentMapper;
import com.patrigod.monument.domain.entity.Monument;
import com.patrigod.monument.domain.repository.GetMonumentByIdRepository;
import com.patrigod.monument.infrastructure.repository.jpa.MonumentRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GetMonumentByIdRepositoryImpl implements GetMonumentByIdRepository {

    private final MonumentRepositoryJpa monumentRepositoryJpa;

    private final MonumentMapper monumentMapper;

    @Override
    public Optional<Monument> getMonumentById(Long id) {
        return monumentRepositoryJpa.findById(id)
                .map(monumentMapper::toMonument);
    }
}
