package com.patrigod.monument.service;


import com.patrigod.monument.domain.entity.Monument;
import org.springframework.stereotype.Service;

import com.patrigod.monument.infrastructure.repository.jpa.entity.MonumentJpa;
import com.patrigod.monument.application.mapper.MonumentMapper;
import com.patrigod.monument.infrastructure.repository.jpa.MonumentRepositoryJpa;

import lombok.RequiredArgsConstructor;

/**
 * Servicio para la gestión de monumentos.
 * Proporciona métodos para consultar, guardar y eliminar monumentos en la base de datos.
 */
@Service
@RequiredArgsConstructor
public class ServiMonumento {

    private final MonumentMapper monumentMapper;
    private final MonumentRepositoryJpa monumentRepositoryJpa;

    /**
     * Obtiene todos los monumentos de la base de datos.
     * @return lista de monumentos en modelo de dominio
     */

    /**
     * Busca y devuelve un monumento por su identificador.
     * @param id identificador del monumento a buscar
     * @return monumento encontrado en modelo de dominio
     * @throws RuntimeException si no se encuentra el monumento
     */
    public Monument findMonumento(Long id){
        MonumentJpa monumentJpa = monumentRepositoryJpa.findById(id).orElseThrow(() -> new RuntimeException("Monumento no encontrado con id: " + id));
        return monumentMapper.toMonument(monumentJpa);
    }


}
