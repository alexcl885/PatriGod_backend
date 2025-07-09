package com.patrigod.monument.infrastructure.controller;

import java.util.List;

import com.patrigod.monument.application.CreateMonumentUseCase;
import com.patrigod.monument.application.DeleteMonumentByIdUseCase;
import com.patrigod.monument.application.GetAllMonumentUseCase;
import com.patrigod.monument.application.GetMonumentByIdUseCase;
import com.patrigod.monument.domain.entity.Monument;
import com.patrigod.monument.infrastructure.controller.dto.input.MonumentInputDto;
import com.patrigod.monument.infrastructure.controller.dto.output.MonumentOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patrigod.monument.application.mapper.MonumentMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/monument")
@RequiredArgsConstructor
public class MonumentController {

    private final MonumentMapper monumentMapper;

    private final GetAllMonumentUseCase getAllMonumentUseCase;
    private final GetMonumentByIdUseCase getMonumentByIdUseCase;
    private final DeleteMonumentByIdUseCase deleteMonumentByIdUseCase;
    private final CreateMonumentUseCase createMonumentUseCase;

    @GetMapping
    public List<MonumentOutputDto> findAll() {
        return getAllMonumentUseCase.getAllMonument()
                .stream()
                .map(monumentMapper::toMonumentOutputDto)
                .toList();
    }

    @PostMapping
    public MonumentOutputDto createMonument(@RequestBody MonumentInputDto monument) {
        Monument monumentObject = monumentMapper.monumentInputDtoToMonument(monument);
        Monument createdMonument = createMonumentUseCase.createMonument(monumentObject, monument.getIdCity());
        return monumentMapper.toMonumentOutputDto(createdMonument);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>  deleteMonumentById(@PathVariable Long id){
        deleteMonumentByIdUseCase.deleteMonumentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT);
    }
    


}
