package com.patrigod.monument.infrastructure.repository.impl;

import com.patrigod.city.infraestructure.repository.jpa.CityRepositoryJpa;
import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.monument.application.mapper.MonumentMapper;
import com.patrigod.monument.domain.entity.Monument;
import com.patrigod.monument.domain.repository.CreateMonumentRepository;
import com.patrigod.monument.infrastructure.repository.jpa.MonumentRepositoryJpa;
import com.patrigod.monument.infrastructure.repository.jpa.entity.MonumentJpa;
import com.patrigod.shared.exception.messages.ErrorMessages;
import com.patrigod.shared.exception.type.EntityNotFoundException;
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

        CityJpa cityJpa = cityRepositoryJpa.findById(idCity)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.CITY_NOT_FOUND_BY_ID_EXCEPTION));

        MonumentJpa monumentJpa = monumentMapper.toMonumentJpa(monument);
        monumentJpa.setCity(cityJpa);
        MonumentJpa createdMonument = monumentRepositoryJpa.save(monumentJpa);
        return monumentMapper.toMonument(createdMonument);
    }
}
