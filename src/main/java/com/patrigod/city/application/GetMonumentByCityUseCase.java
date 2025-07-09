package com.patrigod.city.application;

import com.patrigod.monument.domain.entity.Monument;

import java.util.List;

public interface GetMonumentByCityUseCase {
    List<Monument> getMonumentByCity(Long idMonument);
}
