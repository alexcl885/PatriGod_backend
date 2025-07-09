package com.patrigod.city.application;

import com.patrigod.event.domain.entity.Event;

public interface GetOneEventOfCityUseCase {
    Event getOneEventOfCity(Long idCity, Long idEvent);
}
