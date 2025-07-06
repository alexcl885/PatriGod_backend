package com.patrigod.monument.application;

import com.patrigod.monument.domain.entity.Monument;

public interface GetMonumentByIdUseCase {
    Monument getMonumentById(Long id);
}
