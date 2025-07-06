package com.patrigod.monument.infrastructure.repository.impl;

import com.patrigod.monument.application.mapper.MonumentMapper;
import com.patrigod.monument.domain.repository.DeleteMonumentByIdRepository;
import com.patrigod.monument.infrastructure.repository.jpa.MonumentRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeleteMonumentByIdRepositoryImpl implements DeleteMonumentByIdRepository {

    private final MonumentRepositoryJpa monumentRepositoryJpa;

    private final MonumentMapper monumentMapper;

    @Override
    public void deleteMonumentById(Long id) {
        monumentRepositoryJpa.deleteById(id);
    }
}
