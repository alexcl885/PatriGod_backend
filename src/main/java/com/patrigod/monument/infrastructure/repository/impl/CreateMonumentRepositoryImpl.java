package com.patrigod.monument.infrastructure.repository.impl;

import com.patrigod.city.infraestructure.repository.jpa.CityRepositoryJpa;
import com.patrigod.monument.application.mapper.MonumentMapper;
import com.patrigod.monument.domain.entity.Monument;
import com.patrigod.monument.domain.repository.CreateMonumentRepository;
import com.patrigod.monument.infrastructure.repository.jpa.MonumentRepositoryJpa;
import com.patrigod.monument.infrastructure.repository.jpa.entity.MonumentJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreateMonumentRepositoryImpl implements CreateMonumentRepository {

    private final MonumentRepositoryJpa monumentRepositoryJpa;
    private final CityRepositoryJpa cityRepositoryJpa;

    private final MonumentMapper monumentMapper;

    @Override
    public Monument createMonument(Monument monument, Long idCity) {
        MonumentJpa monumentJpa = monumentMapper.toMonumentJpa(monument);
        MonumentJpa createdMonument = monumentRepositoryJpa.save(monumentJpa);
        return monumentMapper.toMonument(createdMonument);
    }
}
