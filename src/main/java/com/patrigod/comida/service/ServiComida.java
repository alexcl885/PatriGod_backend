package com.patrigod.comida.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patrigod.ciudad.entity.entity.CiudadJpa;
import com.patrigod.ciudad.repository.RepoCiudad;
import com.patrigod.comida.entity.entity.ComidaJpa;
import com.patrigod.comida.entity.model.Comida;
import com.patrigod.comida.mapper.ComidaMapper;
import com.patrigod.comida.repository.RepoComida;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * Servicio para la gestión de comidas.
 * Proporciona métodos para consultar, guardar y eliminar comidas en la base de datos.
 */
@Service
@RequiredArgsConstructor
public class ServiComida {

    private final ComidaMapper comidaMapper;
    
    private final RepoComida repoComida;
    private final RepoCiudad repoCiudad;

    /**
     * Obtiene todas las comidas de la base de datos.
     * @return lista de comidas en modelo de dominio
     */
    public List<Comida> findAll(){
        System.err.println(repoComida.findAll());
        return repoComida.findAll().stream()
                .map(comidaMapper::toModel)
                .toList();
    }

    /**
     * Busca y devuelve una comida por su identificador.
     * @param id identificador de la comida
     * @return comida encontrada en modelo de dominio
     * @throws RuntimeException si no se encuentra la comida
     */
    public Comida findComida(Long id){
        ComidaJpa comida = repoComida.findById(id).orElseThrow(() -> new RuntimeException("Comida no encontrada con id: " + id));
        return comidaMapper.toModel(comida);
    }

    /**
     * Guarda una nueva comida en la base de datos.
     * @param comida objeto comida en modelo de dominio
     * @return comida guardada en modelo de dominio
     */
    public Comida saveComida(Long idCiudad,Comida comida){
        CiudadJpa ciudad = repoCiudad.findById(idCiudad)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada con id: " ));
        ComidaJpa comidaJpa = comidaMapper.toEntity(comida);
        comidaJpa.setCiudad(ciudad);
        return comidaMapper.toModel(repoComida.save(comidaJpa));
    }

    /**
     * Elimina una comida por su identificador.
     * @param id identificador de la comida a eliminar
     * @throws RuntimeException si no se encuentra la comida
     */
    @Transactional
    public Comida deleteComidaById(Long id){
        ComidaJpa comida = repoComida.findById(id)
                .orElseThrow(() -> new RuntimeException("Comida no encontrada con id: " + id));
        repoComida.delete(comida);
        return comidaMapper.toModel(comida);
    }

}
