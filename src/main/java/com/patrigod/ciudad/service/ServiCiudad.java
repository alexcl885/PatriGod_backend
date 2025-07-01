package com.patrigod.ciudad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.patrigod.ciudad.entity.dto.output.RankingArticuloDTO;
import com.patrigod.ciudad.entity.dto.output.RankingCiudadDTO;
import com.patrigod.ciudad.entity.entity.CiudadJpa;
import com.patrigod.ciudad.entity.model.Ciudad;
import com.patrigod.ciudad.mapper.CiudadMapper;
import com.patrigod.ciudad.repository.RepoCiudad;
import com.patrigod.comida.entity.entity.ComidaJpa;
import com.patrigod.comida.repository.RepoComida;
import com.patrigod.evento.entity.entidad.EventoJpa;
import com.patrigod.evento.entity.model.Evento;
import com.patrigod.evento.mapper.EventoMapper;
import com.patrigod.evento.repository.RepoEvento;
import com.patrigod.monumento.entity.entity.MonumentoJpa;
import com.patrigod.monumento.repository.RepoMonumento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiCiudad {

    private final CiudadMapper ciudadMapper;
    private final EventoMapper eventoMapper;

    private final RepoCiudad repoCiudad;
    private final RepoMonumento repoMonumento;
    private final RepoComida repoComida;
    private final RepoEvento repoEvento;

    

    public List<Ciudad> findAll(){
        return repoCiudad.findAll()
                .stream()
                .map(ciudadMapper::toModel)
                .toList();
    }
    /**
     * Metodo que devuelve una ciudad mediante el id
     * @param id ciudad 
     * @return una ciudad 
     */
    public Ciudad findOneCiudad(Long id){
        CiudadJpa ciudadJpa= repoCiudad.findById(id).orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        return ciudadMapper.toModel(ciudadJpa);
    }
    /**
     * Metodo que encuentra los monumentos de una ciudad
     * @param idCiudad parametro de la ciudad
     * @return una lista de monumentos de la ciudad indicada
     */
    public List<MonumentoJpa> findMonumentosByCiudad(Long idCiudad) {
        Optional<CiudadJpa> ciudadOptional = repoCiudad.findById(idCiudad);
        if (ciudadOptional.isPresent()) {
            return repoMonumento.findMonumentoByCiudad(ciudadOptional.get());
        }
        return List.of(); 
    }

    /**
     * Metodo que encuentra las comidas de una ciudad
     * @param idCiudad parametro de la ciudad
     * @return una lista de comidas de la ciudad indicada
     */
    public List<ComidaJpa> findComidasByCiudad(Long idCiudad) {
        Optional<CiudadJpa> ciudadOptional = repoCiudad.findById(idCiudad);
        if (ciudadOptional.isPresent()) {
            return repoComida.findComidaByCiudad(ciudadOptional.get());
        }
        return List.of(); 
    }

    /**
     * Metodo que encuentra los eventos de una ciudad
     * @param idCiudad parametro de la ciudad
     * @return una lista de eventos de la ciudad indicada
     */
    public List<Evento> findEventosByCiudad(Long idCiudad) {
        Optional<CiudadJpa> ciudadOptional = repoCiudad.findById(idCiudad);
        if (ciudadOptional.isPresent()) {
            List<EventoJpa> listEventoJpa = repoEvento.findEventoByCiudad(ciudadOptional.get());
            return listEventoJpa.stream()
                    .map(eventoMapper::toModel)
                    .collect(Collectors.toList());
        }
        return List.of(); 
    }
    

    /**
     * Este método realiza un ranking de las ciudades Patrimonio de la Humanidad
     * basándose en la puntuación promedio de sus Eventos, Monumentos y Comidas.
     * 
     * Pasos:
     * 1. Se realiza una consulta SQL para obtener el ranking de las ciudades con 
     *    su puntuación promedio. Esta información se almacena en una lista de 
     *    objetos `RankingCiudadDTO`.
     * 2. Se crea una lista vacía donde se almacenarán las ciudades completas.
     * 3. Se obtiene una lista de los IDs de las ciudades presentes en el ranking.
     * 4. Con los IDs obtenidos, se consultan todas las ciudades correspondientes 
     *    en la base de datos.
     * 5. Se asocia la puntuación promedio de cada ciudad (proveniente del DTO) 
     *    al objeto `Ciudad` y se agrega a la lista final de ciudades.
     * 6. Se devuelve la lista de ciudades, ahora con su puntuación promedio.
     * 
     * @return Lista de ciudades ordenadas por su puntuación promedio de mayor a menor.
     */

    public List<CiudadJpa> obtenerRankingDeCiudades() {
        List<RankingCiudadDTO> ranking = repoCiudad.findRankingCiudadesByPuntuacionPromedio();
        List<CiudadJpa> ciudadesCompletas = new ArrayList<>();
        List<Long> ciudadIds = ranking.stream()
                                      .map(RankingCiudadDTO::getCiudad_id)
                                      .collect(Collectors.toList());
        
        List<CiudadJpa> ciudades = repoCiudad.findAllById(ciudadIds); // Obtener las ciudades con esos IDs
        for (RankingCiudadDTO dto : ranking) {
            Optional<CiudadJpa> ciudadOpt = ciudades.stream()
                                                 .filter(ciudad -> ciudad.getId().equals(dto.getCiudad_id()))
                                                 .findFirst();
            if (ciudadOpt.isPresent()) {
                CiudadJpa ciudad = ciudadOpt.get();
                ciudad.setPuntuacion(dto.getPuntuacion_promedio());
                ciudadesCompletas.add(ciudad);
            }
        }
        return ciudadesCompletas;
    }    
    /**
     * Devuelve una lista en orden segun la puntuacion de los monumentos
     * de cada ciudad.
     * @return lista de ciudades segun la puntuacion de sus monumentos
     */
    public List<RankingArticuloDTO> findRankingByMonumento(){
        return repoCiudad.findRankingByMonumento();
    }
    /**
     * Devuelve una lista en orden segun la puntuacion de las comidas
     * de cada ciudad.
     * @return lista de ciudades segun la puntuacion de sus comidas
     */
    public List<RankingArticuloDTO> findRankingByComida(){
        return repoCiudad.findRankingByComida();
    }
    /**
     * Devuelve una lista en orden segun la puntuacion de los eventos
     * de cada ciudad.
     * @return lista de ciudades segun la puntuacion de sus eventos
     */
    public List<RankingArticuloDTO> findRankignByEvento(){
        return repoCiudad.findRankingByEvento();
    }

    
}
