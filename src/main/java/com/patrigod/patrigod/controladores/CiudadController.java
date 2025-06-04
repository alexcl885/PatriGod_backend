package com.patrigod.patrigod.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
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

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/ciudad")
@RequiredArgsConstructor
public class CiudadController {

    private final ServiCiudad serviCiudad;
    private final ServiMonumento serviMonumento;
    private final ServiComida serviComida;
    private final ServiEvento serviEvento;

    

    /**
     * Obtiene la lista de todas las ciudades.
     * @return ResponseEntity con la lista de ciudades.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<Ciudad>> findAll() {
        List<Ciudad> ciudades = serviCiudad.findAll();
        return ResponseEntity.ok(ciudades);
    }

    /**
     * Busca una ciudad por su ID.
     * @param id ID de la ciudad
     * @return ResponseEntity con la ciudad encontrada (opcional)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ciudad>> findCiudad(@PathVariable @NonNull Long id) {
        Optional<Ciudad> ciudad = serviCiudad.findOneCiudad(id);
        return ResponseEntity.ok(ciudad);
    }

    /**
     * Obtiene los monumentos de una ciudad por su ID.
     * @param id ID de la ciudad
     * @return ResponseEntity con la lista de monumentos
     */
    @GetMapping("/{id}/monumentos")
    public ResponseEntity<List<Monumento>> findMonumentosByCiudad(@PathVariable @NonNull Long id) {
        List<Monumento> monumentos = serviCiudad.findMonumentosByCiudad(id);
        return ResponseEntity.ok(monumentos);
    }

    /**
     * Obtiene las comidas de una ciudad por su ID.
     * @param id ID de la ciudad
     * @return ResponseEntity con la lista de comidas
     */
    @GetMapping("/{id}/comidas")
    public ResponseEntity<List<Comida>> findComidasByCiudad(@PathVariable @NonNull Long id) {
        List<Comida> comidas = serviCiudad.findComidasByCiudad(id);
        return ResponseEntity.ok(comidas);
    }

    /**
     * Obtiene los eventos de una ciudad por su ID.
     * @param id ID de la ciudad
     * @return ResponseEntity con la lista de eventos
     */
    @GetMapping("/{id}/eventos")
    public ResponseEntity<List<Evento>> findEventosByCiudad(@PathVariable @NonNull Long id) {
        List<Evento> eventos = serviCiudad.findEventosByCiudad(id);
        return ResponseEntity.ok(eventos);
    }

    /**
     * Busca un monumento concreto por su ID.
     * @param idMonumento ID del monumento
     * @return ResponseEntity con el monumento encontrado (opcional)
     */
    @GetMapping("/{id}/monumentos/{idMonumento}")
    public ResponseEntity<Optional<Monumento>> findMonumentoByCiudad(@PathVariable @NonNull Long idMonumento){
        Optional<Monumento> monumento = serviMonumento.findMonumento(idMonumento);
        return ResponseEntity.ok(monumento);
    }

    /**
     * Busca una comida concreta por su ID.
     * @param idComida ID de la comida
     * @return ResponseEntity con la comida encontrada (opcional)
     */
    @GetMapping("/{id}/comidas/{idComida}")
    public ResponseEntity<Optional<Comida>> findComidaByCiudad(@PathVariable @NonNull Long idComida){
        Optional<Comida> comida = serviComida.findComida(idComida);
        return ResponseEntity.ok(comida);
    }

    /**
     * Busca un evento concreto por su ID.
     * @param idEvento ID del evento
     * @return ResponseEntity con el evento encontrado (opcional)
     */
    @GetMapping("/{id}/eventos/{idEvento}")
    public ResponseEntity<Optional<Evento>> findEventoByCiudad(@PathVariable @NonNull Long idEvento){
        Optional<Evento> evento = serviEvento.findEvento(idEvento);
        return ResponseEntity.ok(evento);
    }

    /**
     * Obtiene el ranking de ciudades por puntuación promedio de artículos.
     * @return ResponseEntity con la lista de ciudades ordenadas por ranking
     */
    @GetMapping("/rank")
    public ResponseEntity<List<Ciudad>> ranking() {
        List<Ciudad> ranking = serviCiudad.obtenerRankingDeCiudades();
        return ResponseEntity.ok(ranking);
    }

    /**
     * Obtiene el ranking de ciudades por puntuación media de monumentos.
     * @return ResponseEntity con la lista de DTOs de ranking de monumentos
     */
    @GetMapping("/rank/monumento")
    public ResponseEntity<List<RankingArticuloDTO>> rankingMonumento() {
        List<RankingArticuloDTO> ranking = serviCiudad.findRankingByMonumento();
        return ResponseEntity.ok(ranking);
    }

    /**
     * Obtiene el ranking de ciudades por puntuación media de comidas.
     * @return ResponseEntity con la lista de DTOs de ranking de comidas
     */
    @GetMapping("/rank/comida")
    public ResponseEntity<List<RankingArticuloDTO>> rankingComida() {
        List<RankingArticuloDTO> ranking = serviCiudad.findRankingByComida();
        return ResponseEntity.ok(ranking);
    }

    /**
     * Obtiene el ranking de ciudades por puntuación media de eventos.
     * @return ResponseEntity con la lista de DTOs de ranking de eventos
     */
    @GetMapping("/rank/evento")
    public ResponseEntity<List<RankingArticuloDTO>> rankingEvento() {
        List<RankingArticuloDTO> ranking = serviCiudad.findRankignByEvento();
        return ResponseEntity.ok(ranking);
    }
}
