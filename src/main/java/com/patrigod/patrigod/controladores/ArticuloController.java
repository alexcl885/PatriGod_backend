package com.patrigod.patrigod.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Articulo;
import com.patrigod.patrigod.modelos.Monumento;
import com.patrigod.patrigod.servicios.ServiArticulo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/articulo")
public class ArticuloController {

    @Autowired
    private ServiArticulo serviArticulo;
    
    @GetMapping
    public ResponseEntity<List<Monumento>> getAllArticulos() {
        List<Monumento> articulos = serviArticulo.findAllMonumentos();
        return ResponseEntity.ok(articulos);  // Esto debería devolver los artículos con sus tipos correctamente serializados
    }


    
}
