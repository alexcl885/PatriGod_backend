package com.patrigod.event.infraestructure.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.event.infraestructure.repository.jpa.entity.EventJpa;

@Repository
public interface EventRepositoryJpa extends JpaRepository<EventJpa, Long> {
    List<EventJpa> findEventByCity(CityJpa ciudad);

}
