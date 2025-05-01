package com.patrigod.patrigod.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private RepoCiudad repoCiudad;

    @Autowired
    private RepoMonumento repoMonumento;

    @Autowired 
    private RepoComida repoComida;

    @Autowired 
    private RepoEvento repoEvento;

    public List<Ciudad> findAll(){
        System.err.println(repoCiudad.findAll());
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
    

    

    /*public List<Ciudad> obtenerRankingDeCiudades() {
        List<RankingCiudadDTO> ranking = repoCiudad.findRankingCiudadesByPuntuacionPromedio();
        
        List<Ciudad> ciudadesCompletas = new ArrayList<>();
        
        for (RankingCiudadDTO dto : ranking) {
            // Obtener la ciudad completa por su ID
            Optional<Ciudad> ciudadOpt = repoCiudad.findById(dto.getCiudadId());
            if (ciudadOpt.isPresent()) {
                Ciudad ciudad = ciudadOpt.get();
                // Aquí puedes asociar la puntuación promedio al objeto Ciudad si lo deseas
                // ciudad.setPuntuacionPromedio(dto.getPuntuacionPromedio());
                ciudadesCompletas.add(ciudad);
            }
        }
        
        return ciudadesCompletas;
    }*/
    
}
