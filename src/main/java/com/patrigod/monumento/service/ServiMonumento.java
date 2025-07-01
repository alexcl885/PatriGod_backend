package com.patrigod.monumento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patrigod.ciudad.entity.entity.CiudadJpa;
import com.patrigod.ciudad.repository.RepoCiudad;
import com.patrigod.monumento.entity.entity.MonumentoJpa;
import com.patrigod.monumento.entity.model.Monumento;
import com.patrigod.monumento.mapper.MonumentoMapper;
import com.patrigod.monumento.repository.RepoMonumento;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * Servicio para la gestión de monumentos.
 * Proporciona métodos para consultar, guardar y eliminar monumentos en la base de datos.
 */
@Service
@RequiredArgsConstructor
public class ServiMonumento {

    private final MonumentoMapper monumentoMapper;
    private final RepoCiudad repoCiudad;
    private final RepoMonumento repoMonumento;

    /**
     * Obtiene todos los monumentos de la base de datos.
     * @return lista de monumentos en modelo de dominio
     */
    public List<Monumento> findAll(){
        System.err.println(repoMonumento.findAll());
        return repoMonumento.findAll().stream()
                .map(monumentoMapper::toModel)
                .toList();
    }

    /**
     * Busca y devuelve un monumento por su identificador.
     * @param id identificador del monumento a buscar
     * @return monumento encontrado en modelo de dominio
     * @throws RuntimeException si no se encuentra el monumento
     */
    public Monumento findMonumento(Long id){
        MonumentoJpa monumentoJpa = repoMonumento.findById(id).orElseThrow(() -> new RuntimeException("Monumento no encontrado con id: " + id));
        return monumentoMapper.toModel(monumentoJpa);
    }

    /**
     * Guarda un nuevo monumento en la base de datos.
     * @param monumento objeto monumento en modelo de dominio
     * @return monumento guardado en modelo de dominio
     */
    public Monumento saveMonumento(Long idCiudad,Monumento monumento){
        CiudadJpa ciudadJpa = repoCiudad.findById(idCiudad)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con id: " + idCiudad));        
        MonumentoJpa monumentoJpa = monumentoMapper.toEntity(monumento);
        monumentoJpa.setCiudad(ciudadJpa);
        MonumentoJpa monumentoCreado = repoMonumento.save(monumentoJpa);
        return monumentoMapper.toModel(monumentoCreado);
    }

    /**
     * Elimina un monumento por su identificador.
     * @param id identificador del monumento a eliminar
     * @throws RuntimeException si no se encuentra el monumento
     */
    @Transactional
    public Monumento deleteMonumentoById(Long id){
        MonumentoJpa monumento = repoMonumento.findById(id)
                .orElseThrow(() -> new RuntimeException("Monumento no encontrado con id: " + id));
        repoMonumento.delete(monumento);
        return monumentoMapper.toModel(monumento);
    }

}
