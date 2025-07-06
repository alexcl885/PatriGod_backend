package com.patrigod.food.infrastructure.controller;

import java.util.List;

import com.patrigod.food.domain.entity.Food;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.food.infrastructure.controller.dto.input.FoodInputDto;
import com.patrigod.food.infrastructure.controller.dto.output.FoodOutputDto;
import com.patrigod.food.application.mapper.FoodMapper;
import com.patrigod.food.service.ServiComida;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodMapper foodMapper;
    
    private final ServiComida serviComida;

    /**
     * Obtiene la lista de todas las comidas.
     * @return lista de comidas en formato DTO de salida
     */
    @GetMapping
    public List<FoodOutputDto> findAll() {
        return serviComida.findAll().stream()
                .map(foodMapper::toOutputDto)
                .toList();
    }

    /**
     * Guarda una nueva comida en la base de datos.
     * @param comida DTO de entrada con los datos de la comida
     * @return la comida guardada en formato DTO de salida
     */
    @PostMapping
    public FoodOutputDto saveComida(@RequestBody FoodInputDto comida) {
        Food foodObject = foodMapper.toInputDto(comida);
        return foodMapper.toOutputDto(serviComida.saveComida(comida.getCityId(), foodObject));
    }

    /**
     * Elimina una comida por su identificador.
     * @param id identificador de la comida a eliminar
     * @return ResponseEntity con la comida eliminada en formato DTO de salida
     */
    @DeleteMapping("{id}")
    public ResponseEntity<FoodOutputDto>  deleteComida(@PathVariable Long id){
        Food food = serviComida.deleteComidaById(id);
        return new ResponseEntity<>(foodMapper.toOutputDto(food), HttpStatus.OK);

    }

}
