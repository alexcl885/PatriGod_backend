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

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/ciudad")
public class CiudadController {

    private final ServiCiudad serviCiudad;
    private final ServiMonumento serviMonumento;
    private final ServiComida serviComida;
    private final ServiEvento serviEvento;

    public CiudadController(ServiCiudad serviCiudad, ServiMonumento serviMonumento,
                            ServiComida serviComida, ServiEvento serviEvento) {
        this.serviCiudad = serviCiudad;
        this.serviMonumento = serviMonumento;
        this.serviComida = serviComida;
        this.serviEvento = serviEvento;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<Ciudad>> findAll() {
        List<Ciudad> ciudades = serviCiudad.findAll();
        return ResponseEntity.ok(ciudades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ciudad>> findCiudad(@PathVariable @NonNull Long id) {
        Optional<Ciudad> ciudad = serviCiudad.findOneCiudad(id);
        return ResponseEntity.ok(ciudad);
    }

    @GetMapping("/{id}/monumentos")
    public ResponseEntity<List<Monumento>> findMonumentosByCiudad(@PathVariable @NonNull Long id) {
        List<Monumento> monumentos = serviCiudad.findMonumentosByCiudad(id);
        return ResponseEntity.ok(monumentos);
    }

    @GetMapping("/{id}/comidas")
    public ResponseEntity<List<Comida>> findComidasByCiudad(@PathVariable @NonNull Long id) {
        List<Comida> comidas = serviCiudad.findComidasByCiudad(id);
        return ResponseEntity.ok(comidas);
    }

    @GetMapping("/{id}/eventos")
    public ResponseEntity<List<Evento>> findEventosByCiudad(@PathVariable @NonNull Long id) {
        List<Evento> eventos = serviCiudad.findEventosByCiudad(id);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}/monumentos/{idMonumento}")
    public ResponseEntity<Optional<Monumento>> findMonumentoByCiudad(@PathVariable @NonNull Long idMonumento){
        Optional<Monumento> monumento = serviMonumento.findMonumento(idMonumento);
        return ResponseEntity.ok(monumento);
    }

    @GetMapping("/{id}/comidas/{idComida}")
    public ResponseEntity<Optional<Comida>> findComidaByCiudad(@PathVariable @NonNull Long idComida){
        Optional<Comida> comida = serviComida.findComida(idComida);
        return ResponseEntity.ok(comida);
    }

    @GetMapping("/{id}/eventos/{idEvento}")
    public ResponseEntity<Optional<Evento>> findEventoByCiudad(@PathVariable @NonNull Long idEvento){
        Optional<Evento> evento = serviEvento.findEvento(idEvento);
        return ResponseEntity.ok(evento);
    }

    @GetMapping("/rank")
    public ResponseEntity<List<Ciudad>> ranking() {
        List<Ciudad> ranking = serviCiudad.obtenerRankingDeCiudades();
        return ResponseEntity.ok(ranking);
    }

    @GetMapping("/rank/monumento")
    public ResponseEntity<List<RankingArticuloDTO>> rankingMonumento() {
        List<RankingArticuloDTO> ranking = serviCiudad.findRankingByMonumento();
        return ResponseEntity.ok(ranking);
    }

    @GetMapping("/rank/comida")
    public ResponseEntity<List<RankingArticuloDTO>> rankingComida() {
        List<RankingArticuloDTO> ranking = serviCiudad.findRankingByComida();
        return ResponseEntity.ok(ranking);
    }

    @GetMapping("/rank/evento")
    public ResponseEntity<List<RankingArticuloDTO>> rankingEvento() {
        List<RankingArticuloDTO> ranking = serviCiudad.findRankignByEvento();
        return ResponseEntity.ok(ranking);
    }
}
