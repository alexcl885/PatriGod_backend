package com.patrigod.event.service;

import java.util.List;

import com.patrigod.event.domain.entity.Event;
import org.springframework.stereotype.Service;

import com.patrigod.city.infraestructure.repository.jpa.entity.CityJpa;
import com.patrigod.city.infraestructure.repository.jpa.CityRepositoryJpa;
import com.patrigod.event.infraestructure.repository.jpa.entity.EventJpa;
import com.patrigod.event.application.mapper.EventMapper;
import com.patrigod.event.infraestructure.repository.jpa.EventRepositoryJpa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiEvento {

    private final EventMapper eventMapper;

    private final CityRepositoryJpa cityRepositoryJpa;
    private final EventRepositoryJpa eventRepositoryJpa;

    

    /**
     * Devuelve todos los eventos existentes en la base de datos.
     * @return lista de eventos en modelo de dominio
     */
    public List<Event> findAll() {
        System.err.println(eventRepositoryJpa.findAll());
        return eventRepositoryJpa.findAll().stream()
                .map(eventMapper::toModel)
                .toList();
    }

    /**
     * Busca y devuelve un evento por su identificador.
     * @param id identificador del evento
     * @return evento encontrado en modelo de dominio
     * @throws RuntimeException si no se encuentra el evento
     */
    public Event findEvento(Long id) {
        EventJpa eventJpa =  eventRepositoryJpa.findById(id).orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
        return eventMapper.toModel(eventJpa);
    }

    /**
     * Guarda un nuevo evento en la base de datos.
     * @param event objeto evento en modelo de dominio
     * @return evento guardado en modelo de dominio
     */
    public Event saveEvento(Long idCiudad, Event event) {
        CityJpa cityJpa = cityRepositoryJpa.findById(idCiudad)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con id: " + idCiudad));
        EventJpa eventJpa = eventMapper.toEntity(event);
        eventJpa.setCity(cityJpa);
        return eventMapper.toModel(eventRepositoryJpa.save(eventJpa));
    }

    /**
     * Elimina un evento por su identificador.
     * @param id identificador del evento a eliminar
     * @throws RuntimeException si no se encuentra el evento
     */
    @Transactional
    public void deleteEventoById(Long id) {
        EventJpa evento = eventRepositoryJpa.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
        eventRepositoryJpa.delete(evento);
    }

}
