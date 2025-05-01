package com.patrigod.patrigod.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.modelos.Ciudad;
import com.patrigod.patrigod.modelos.Comida;
import com.patrigod.patrigod.modelos.Evento;
import com.patrigod.patrigod.modelos.Monumento;
import com.patrigod.patrigod.servicios.ServiCiudad;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/ciudad")
public class CiudadController {
    @Autowired
    private ServiCiudad serviCiudad;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Ciudad> findAll() {
        return serviCiudad.findAll();
    }
    /**
     * 
     * @param id identificador de la ciudad
     * @return una ciudad
     */
    @GetMapping("/{id}")
    public Optional<Ciudad> findCiudad(@PathVariable @NonNull Long id) {
        return serviCiudad.findOneCiudad(id);
    }

    /**
     * 
     * @param id indentificar de la ciudad
     * @return una lista de monumentos de la ciudad elegida por id.
     */
    @GetMapping("/{id}/monumentos")
    public List<Monumento> findMonumentosByCiudad(@PathVariable @NonNull Long id) {
        return serviCiudad.findMonumentosByCiudad(id);
    }
    

    /**
     * 
     * @param id indentificar de la ciudad
     * @return una lista de monumentos de la ciudad elegida por id.
     */
    @GetMapping("/{id}/comidas")
    public List<Comida> findComidasByCiudad(@PathVariable @NonNull Long id) {
        return serviCiudad.findComidasByCiudad(id);
    }
    

    /**
     * 
     * @param id indentificar de la ciudad
     * @return una lista de monumentos de la ciudad elegida por id.
     */
    @GetMapping("/{id}/eventos")
    public List<Evento> findEventosByCiudad(@PathVariable @NonNull Long id) {
        return serviCiudad.findEventosByCiudad(id);
    }

    

    /*@GetMapping("/rank")
    public List<Ciudad> ranking() {
        return serviCiudad.obtenerRankingDeCiudades ();
    }*/


    
}
