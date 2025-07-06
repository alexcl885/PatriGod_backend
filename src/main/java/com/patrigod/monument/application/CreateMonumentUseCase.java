package com.patrigod.monument.application;

import com.patrigod.monument.domain.entity.Monument;

public interface CreateMonumentUseCase {
    Monument createMonument(Monument monument, Long idCity);
}
