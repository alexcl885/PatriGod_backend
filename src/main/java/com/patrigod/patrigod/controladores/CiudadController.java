package com.patrigod.patrigod.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.patrigod.DTO.RankingArticuloDTO;
import com.patrigod.patrigod.modelos.Ciudad;
import com.patrigod.patrigod.modelos.Comida;
import com.patrigod.patrigod.modelos.Evento;
import com.patrigod.patrigod.modelos.Monumento;
import com.patrigod.patrigod.servicios.ServiCiudad;
import com.patrigod.patrigod.servicios.ServiComida;
import com.patrigod.patrigod.servicios.ServiEvento;
import com.patrigod.patrigod.servicios.ServiMonumento;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/ciudad")
public class CiudadController {
    @Autowired
    private ServiCiudad serviCiudad;

    @Autowired
    private ServiMonumento serviMonumento;

    @Autowired
    private ServiComida serviComida;

    @Autowired
    private ServiEvento serviEvento;

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

     /**
     * 
     * @param id indentificar de la ciudad
     * @return un monumento de una ciudad
     */
    @GetMapping("/{id}/monumentos/{idMonumento}")
    public Optional<Monumento> findMonumentoByCiudad(@PathVariable @NonNull Long idMonumento){
        return serviMonumento.findMonumento(idMonumento);
    }
    
    /**
     * 
     * @param id indentificar de la ciudad
     * @return una comida de una ciudad
     */
    @GetMapping("/{id}/comidas/{idComida}")
    public Optional<Comida> findComidaByCiudad(@PathVariable @NonNull Long idComida){
        return serviComida.findComida(idComida);
    }


    /**
     * 
     * @param id indentificar de la ciudad
     * @return una comida de una ciudad
     */
    @GetMapping("/{id}/eventos/{idEvento}")
    public Optional<Evento> findEventoByCiudad(@PathVariable @NonNull Long idEvento){
        return serviEvento.findEvento(idEvento);
    }
    /**
     * 
     * @return lista de ciudades rankeadas por la suma de sus articulos
     */
    @GetMapping("/rank")
    public List<Ciudad> ranking() {
        return serviCiudad.obtenerRankingDeCiudades ();
    }
    /**
     * 
     * @return lista ciudades(DTO) segun la media de monumentos de cada ciudad
     */
    @GetMapping("/rankMonumento")
    public List<RankingArticuloDTO> rankingMonumento() {
        return serviCiudad.findRankingByMonumento();
    }
    /**
     * 
     * @return lista ciudades(DTO) segun la media de comidas de cada ciudad
     */
    @GetMapping("/rankComida")
    public List<RankingArticuloDTO> rankingComida() {
        return serviCiudad.findRankingByComida();
    }

    /**
     * 
     * @return lista ciudades(DTO) segun la media de eventos de cada ciudad
     */
    @GetMapping("/rankEvento")
    public List<RankingArticuloDTO> rankingEvento() {
        return serviCiudad.findRankignByEvento();
    }


    

    
}
