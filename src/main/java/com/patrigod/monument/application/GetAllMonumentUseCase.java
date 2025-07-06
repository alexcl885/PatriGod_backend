package com.patrigod.monument.application;

import com.patrigod.monument.domain.entity.Monument;

import java.util.List;

public interface GetAllMonumentUseCase {
    List<Monument> getAllMonument();
}
