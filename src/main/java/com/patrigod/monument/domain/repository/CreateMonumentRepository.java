package com.patrigod.monument.domain.repository;

import com.patrigod.monument.domain.entity.Monument;

public interface CreateMonumentRepository {
    Monument createMonument(Monument monument, Long idCity);
}
