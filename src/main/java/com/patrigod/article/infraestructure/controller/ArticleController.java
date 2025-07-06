package com.patrigod.article.infraestructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/articulo")
@RequiredArgsConstructor
public class ArticleController {


    //private final GetAllArticleUseCase getAllArticleUseCase;


    /**
     * Retrieves the list of all articles.
     * @return ResponseEntity containing the list of found articles.
     */
    /*@GetMapping
    public ResponseEntity<List<ArticleOutputDto>> getAllArticulos() {
        List<ArticleOutputDto> articles = getAllArticleUseCase.getAllArticle()
                .stream()
                .map(articleMapper::toOutputDto)
                .toList();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }*/

    
}
