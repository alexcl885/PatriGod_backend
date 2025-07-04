package com.patrigod.article.infraestructure.repository.impl;

import com.patrigod.article.application.mapper.ArticleMapper;
import com.patrigod.article.domain.entity.Article;
import com.patrigod.article.domain.repository.GetAllArticleRepository;
import com.patrigod.article.infraestructure.repository.jpa.ArticleRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GetAllArticleRepositoryImpl implements GetAllArticleRepository {

    private final ArticleMapper articleMapper;

    private final ArticleRepositoryJpa articleRepositoryJpa;

    @Override
    public List<Article> getAllArticle() {
        return articleRepositoryJpa.findAll()
                .stream()
                .map(articleMapper::toModel)
                .toList();
    }
}
