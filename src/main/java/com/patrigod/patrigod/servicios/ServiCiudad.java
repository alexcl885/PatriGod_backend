package com.patrigod.patrigod.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.patrigod.patrigod.DTO.RankingArticuloDTO;
import com.patrigod.patrigod.DTO.RankingCiudadDTO;
import com.patrigod.patrigod.modelos.Ciudad;
import com.patrigod.patrigod.modelos.Comida;
import com.patrigod.patrigod.modelos.Evento;
import com.patrigod.patrigod.modelos.Monumento;
import com.patrigod.patrigod.repos.RepoCiudad;
import com.patrigod.patrigod.repos.RepoComida;
import com.patrigod.patrigod.repos.RepoEvento;
import com.patrigod.patrigod.repos.RepoMonumento;

@Service
public class ServiCiudad {

    private final RepoCiudad repoCiudad;
    private final RepoMonumento repoMonumento;
    private final RepoComida repoComida;
    private final RepoEvento repoEvento;

    public ServiCiudad(RepoCiudad repoCiudad, RepoMonumento repoMonumento,
                       RepoComida repoComida, RepoEvento repoEvento) {
        this.repoCiudad = repoCiudad;
        this.repoMonumento = repoMonumento;
        this.repoComida = repoComida;
        this.repoEvento = repoEvento;
    }

    public List<Ciudad> findAll(){
        return repoCiudad.findAll();
    }
    /**
     * Metodo que devuelve una ciudad mediante el id
     * @param id ciudad 
     * @return una ciudad 
     */
    public Optional<Ciudad> findOneCiudad(Long id){
        return repoCiudad.findById(id);
    }
    /**
     * Metodo que encuentra los monumentos de una ciudad
     * @param idCiudad parametro de la ciudad
     * @return una lista de monumentos de la ciudad indicada
     */
    public List<Monumento> findMonumentosByCiudad(Long idCiudad) {
        Optional<Ciudad> ciudadOptional = repoCiudad.findById(idCiudad);
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
    public List<Comida> findComidasByCiudad(Long idCiudad) {
        Optional<Ciudad> ciudadOptional = repoCiudad.findById(idCiudad);
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
        Optional<Ciudad> ciudadOptional = repoCiudad.findById(idCiudad);
        if (ciudadOptional.isPresent()) {
            return repoEvento.findEventoByCiudad(ciudadOptional.get());
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

    public List<Ciudad> obtenerRankingDeCiudades() {
        List<RankingCiudadDTO> ranking = repoCiudad.findRankingCiudadesByPuntuacionPromedio();
        List<Ciudad> ciudadesCompletas = new ArrayList<>();
        List<Long> ciudadIds = ranking.stream()
                                      .map(RankingCiudadDTO::getCiudad_id)
                                      .collect(Collectors.toList());
        
        List<Ciudad> ciudades = repoCiudad.findAllById(ciudadIds); // Obtener las ciudades con esos IDs
        for (RankingCiudadDTO dto : ranking) {
            Optional<Ciudad> ciudadOpt = ciudades.stream()
                                                 .filter(ciudad -> ciudad.getId().equals(dto.getCiudad_id()))
                                                 .findFirst();
            if (ciudadOpt.isPresent()) {
                Ciudad ciudad = ciudadOpt.get();
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
