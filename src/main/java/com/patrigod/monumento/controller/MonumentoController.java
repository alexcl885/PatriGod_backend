package com.patrigod.monumento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.article.infraestructure.repository.jpa.ArticleRepositoryJpa;
import com.patrigod.monumento.entity.dto.input.MonumentoInputDto;
import com.patrigod.monumento.entity.dto.output.MonumentoOutputDto;
import com.patrigod.monumento.entity.model.Monumento;
import com.patrigod.monumento.mapper.MonumentoMapper;
import com.patrigod.monumento.service.ServiMonumento;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/monumento")
@RequiredArgsConstructor
public class MonumentoController {

    private final MonumentoMapper monumentoMapper;
    private final ArticleRepositoryJpa articleRepositoryJpa;
    
    private final ServiMonumento serviMonumento;

    @GetMapping
    public List<MonumentoOutputDto> findAll() {
        return serviMonumento.findAll()
                .stream()
                .map(monumentoMapper::toOutputDto)
                .toList();
    }

    @PostMapping
    public MonumentoOutputDto saveMonumento(@RequestBody MonumentoInputDto monumento) {
        Monumento monumentoObject = monumentoMapper.toInputDto(monumento);
        Monumento monumentoCreado = serviMonumento.saveMonumento(monumento.getIdCiudad(),monumentoObject);
        return monumentoMapper.toOutputDto(monumentoCreado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MonumentoOutputDto>  deleteEvento(@PathVariable Long id){
        Monumento monumento = serviMonumento.deleteMonumentoById(id);
        articleRepositoryJpa.deleteById(id);
        return new ResponseEntity<>(monumentoMapper.toOutputDto(monumento), HttpStatus.OK);
    }
    


}
