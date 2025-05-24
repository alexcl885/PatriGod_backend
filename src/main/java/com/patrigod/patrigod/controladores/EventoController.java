package com.patrigod.patrigod.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Evento;
import com.patrigod.patrigod.servicios.ServiEvento;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/evento")
public class EventoController {
    
    private final ServiEvento serviEvento;

    public EventoController(ServiEvento serviEvento) {
        this.serviEvento = serviEvento;
    }


    @GetMapping
    public List<Evento> findAll() {
        return serviEvento.findAll();
    }

    @PostMapping
    public Evento saveEvento(@RequestBody Evento evento) {        
        return serviEvento.saveEvento(evento);
        
    }
    /***
     * NO SE PUEDEN BORRAR EVENTOS QUE TENGAN PUNTUACIONES...
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?>  deleteEvento(@PathVariable Long id){
        Optional<Evento> evento = serviEvento.findEvento(id);
        if (evento.isPresent()){
            serviEvento.deleteEventoById(id);
            return new ResponseEntity<>(evento.get(), HttpStatus.OK);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ese usuario");
        }
    }
    


}
