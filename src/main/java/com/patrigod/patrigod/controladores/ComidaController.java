package com.patrigod.patrigod.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Comida;
import com.patrigod.patrigod.modelos.Evento;
import com.patrigod.patrigod.servicios.ServiComida;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/comida")
public class ComidaController {
    @Autowired
    private ServiComida serviComida;

    @GetMapping
    public List<Comida> findAll() {
        return serviComida.findAll();
    }

    @PostMapping
    public Comida saveComida(@RequestBody Comida comida) {        
        return serviComida.saveComida(comida);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?>  deleteComida(@PathVariable Long id){
        Optional<Comida> comida = serviComida.findComida(id);
        if (comida.isPresent()){
            serviComida.deleteComidaById(id);
            return new ResponseEntity<>(comida.get(), HttpStatus.OK);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ese usuario");
        }
    }

}
