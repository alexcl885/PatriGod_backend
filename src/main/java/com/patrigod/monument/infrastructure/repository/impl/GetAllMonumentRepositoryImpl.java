package com.patrigod.monument.infrastructure.repository.impl;

import com.patrigod.monument.application.mapper.MonumentMapper;
import com.patrigod.monument.domain.entity.Monument;
import com.patrigod.monument.domain.repository.GetAllMonumentRepository;
import com.patrigod.monument.infrastructure.repository.jpa.MonumentRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GetAllMonumentRepositoryImpl implements GetAllMonumentRepository {

    private final MonumentRepositoryJpa monumentRepositoryJpa;

    private final MonumentMapper monumentMapper;

    @Override
    public List<Monument> getAllMonument() {
        return monumentRepositoryJpa.findAll()
                .stream()
                .map(monumentMapper::toMonument)
                .toList();
    }
}
