package com.patrigod.patrigod.comida.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.comida.entity.dto.input.ComidaInputDto;
import com.patrigod.patrigod.comida.entity.dto.output.ComidaOutputDto;
import com.patrigod.patrigod.comida.entity.model.Comida;
import com.patrigod.patrigod.comida.mapper.ComidaMapper;
import com.patrigod.patrigod.comida.service.ServiComida;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/comida")
@RequiredArgsConstructor
public class ComidaController {

    private final ComidaMapper comidaMapper;
    
    private final ServiComida serviComida;

    /**
     * Obtiene la lista de todas las comidas.
     * @return lista de comidas en formato DTO de salida
     */
    @GetMapping
    public List<ComidaOutputDto> findAll() {
        return serviComida.findAll().stream()
                .map(comidaMapper::toOutputDto)
                .toList();
    }

    /**
     * Guarda una nueva comida en la base de datos.
     * @param comida DTO de entrada con los datos de la comida
     * @return la comida guardada en formato DTO de salida
     */
    @PostMapping
    public ComidaOutputDto saveComida(@RequestBody ComidaInputDto comida) {   
        Comida comidaObject = comidaMapper.toInputDto(comida);     
        return comidaMapper.toOutputDto(serviComida.saveComida(comida.getIdCiudad(),comidaObject));
    }

    /**
     * Elimina una comida por su identificador.
     * @param id identificador de la comida a eliminar
     * @return ResponseEntity con la comida eliminada en formato DTO de salida
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ComidaOutputDto>  deleteComida(@PathVariable Long id){
        Comida comida = serviComida.deleteComidaById(id);
        return new ResponseEntity<>(comidaMapper.toOutputDto(comida), HttpStatus.OK);

    }

}
