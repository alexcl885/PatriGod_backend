package com.patrigod.monument.domain.repository;

import com.patrigod.monument.domain.entity.Monument;

import java.util.Optional;

public interface GetMonumentByIdRepository {
    Optional<Monument> getMonumentById(Long id);
}
