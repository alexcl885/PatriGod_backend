package com.patrigod.patrigod.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.patrigod.patrigod.modelos.Monumento;
import com.patrigod.patrigod.repos.RepoMonumento;

@Service
public class ServiMonumento {
    
    private final RepoMonumento repoMonumento;

    public ServiMonumento(RepoMonumento repoMonumento) {
        this.repoMonumento = repoMonumento;
    }
    
    /**
     * 
     * @return todos los monumentos de la base de datos
     */
    public List<Monumento> findAll(){
        System.err.println(repoMonumento.findAll());
        return repoMonumento.findAll();
    }
    /**
     * 
     * @param id del monumento a buscar
     * @return un monumento por su identificador
     */
    public Optional<Monumento> findMonumento(Long id){
        return repoMonumento.findById(id);
    }
    /**
     * 
     * @param monumento parametro para guardar un nuevo monumento
     * @return un nuevo monumento en la base de datos
     */
    public Monumento saveMonumento(Monumento monumento){
        return repoMonumento.save(monumento);
    }

    public void deleteMonumentoById(Long id){
        repoMonumento.deleteById(id);
    }



}
