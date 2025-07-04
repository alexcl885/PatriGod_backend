package com.patrigod.article.infraestructure.controller;

import java.util.List;

import com.patrigod.article.application.GetAllArticleUseCase;
import com.patrigod.article.application.mapper.ArticleMapper;
import com.patrigod.article.infraestructure.controller.dto.output.ArticleOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/articulo")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleMapper articleMapper;
    
    private final GetAllArticleUseCase getAllArticleUseCase;


    /**
     * Retrieves the list of all articles.
     * @return ResponseEntity containing the list of found articles.
     */
    @GetMapping
    public ResponseEntity<List<ArticleOutputDto>> getAllArticulos() {
        List<ArticleOutputDto> articles = getAllArticleUseCase.getAllArticle()
                .stream()
                .map(articleMapper::toOutputDto)
                .toList();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    
}
