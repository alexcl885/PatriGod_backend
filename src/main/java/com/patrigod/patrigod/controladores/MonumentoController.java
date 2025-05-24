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

import com.patrigod.patrigod.modelos.Evento;
import com.patrigod.patrigod.modelos.Monumento;
import com.patrigod.patrigod.servicios.ServiMonumento;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/monumento")
public class MonumentoController {
    @Autowired
    private ServiMonumento serviMonumento;

    @GetMapping
    public List<Monumento> findAll() {
        return serviMonumento.findAll();
    }

    @PostMapping
    public Monumento saveMonumento(@RequestBody Monumento monumento) {
        return serviMonumento.saveMonumento(monumento);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?>  deleteEvento(@PathVariable Long id){
        Optional<Monumento> monumento = serviMonumento.findMonumento(id);
        if (monumento.isPresent()){
            serviMonumento.deleteMonumentoById(id);
            return new ResponseEntity<>(monumento.get(), HttpStatus.OK);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ese usuario");
        }
    }
    


}
