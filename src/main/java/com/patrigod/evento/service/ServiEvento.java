package com.patrigod.evento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patrigod.ciudad.entity.entity.CiudadJpa;
import com.patrigod.ciudad.repository.RepoCiudad;
import com.patrigod.evento.entity.entidad.EventoJpa;
import com.patrigod.evento.entity.model.Evento;
import com.patrigod.evento.mapper.EventoMapper;
import com.patrigod.evento.repository.RepoEvento;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiEvento {

    private final EventoMapper eventoMapper;

    private final RepoCiudad repoCiudad;
    private final RepoEvento repoEvento;

    

    /**
     * Devuelve todos los eventos existentes en la base de datos.
     * @return lista de eventos en modelo de dominio
     */
    public List<Evento> findAll() {
        System.err.println(repoEvento.findAll());
        return repoEvento.findAll().stream()
                .map(eventoMapper::toModel)
                .toList();
    }

    /**
     * Busca y devuelve un evento por su identificador.
     * @param id identificador del evento
     * @return evento encontrado en modelo de dominio
     * @throws RuntimeException si no se encuentra el evento
     */
    public Evento findEvento(Long id) {
        EventoJpa eventoJpa =  repoEvento.findById(id).orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
        return eventoMapper.toModel(eventoJpa);
    }

    /**
     * Guarda un nuevo evento en la base de datos.
     * @param evento objeto evento en modelo de dominio
     * @return evento guardado en modelo de dominio
     */
    public Evento saveEvento(Long idCiudad,Evento evento) {
        CiudadJpa ciudadJpa = repoCiudad.findById(idCiudad)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con id: " + idCiudad));
        EventoJpa eventoJpa = eventoMapper.toEntity(evento);
        eventoJpa.setCiudad(ciudadJpa);
        return eventoMapper.toModel(repoEvento.save(eventoJpa));
    }

    /**
     * Elimina un evento por su identificador.
     * @param id identificador del evento a eliminar
     * @throws RuntimeException si no se encuentra el evento
     */
    @Transactional
    public void deleteEventoById(Long id) {
        EventoJpa evento = repoEvento.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
        repoEvento.delete(evento);
    }

}
