package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Articulo;
import com.patrigod.patrigod.servicios.ServiArticulo;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/articulo")
public class ArticuloController {
    
    private final ServiArticulo serviArticulo;

    public ArticuloController(ServiArticulo serviArticulo) {
        this.serviArticulo = serviArticulo;
    }

    
    @GetMapping
    public ResponseEntity<List<Articulo>> getAllArticulos() {
    return ResponseEntity.ok(serviArticulo.findAll());
}


    
}
