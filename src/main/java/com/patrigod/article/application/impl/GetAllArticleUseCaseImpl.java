package com.patrigod.article.application.impl;

import com.patrigod.article.application.GetAllArticleUseCase;
import com.patrigod.article.domain.entity.Article;
import com.patrigod.article.domain.repository.GetAllArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllArticleUseCaseImpl implements GetAllArticleUseCase {

    private final GetAllArticleRepository getAllArticleRepository;

    @Override
    public List<Article> getAllArticle() {
        return getAllArticleRepository.getAllArticle();
    }
}
