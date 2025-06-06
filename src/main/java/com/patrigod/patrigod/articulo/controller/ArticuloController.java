package com.patrigod.patrigod.articulo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.articulo.entity.entity.ArticuloJpa;
import com.patrigod.patrigod.articulo.service.ServiArticulo;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/articulo")
@RequiredArgsConstructor
public class ArticuloController {
    
    private final ServiArticulo serviArticulo;


    /**
     * Obtiene la lista de todos los artículos.
     * @return ResponseEntity con la lista de artículos encontrados.
     */
    @GetMapping
    public ResponseEntity<List<ArticuloJpa>> getAllArticulos() {
        return ResponseEntity.ok(serviArticulo.findAll());
    }

    
}
