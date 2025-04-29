package com.patrigod.patrigod.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Ciudad;
import com.patrigod.patrigod.repos.RepoCiudad;

@Service
public class ServiCiudad {
    @Autowired
    private RepoCiudad repoCiudad;

    public List<Ciudad> findAll(){
        System.err.println(repoCiudad.findAll());
        return repoCiudad.findAll();
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
