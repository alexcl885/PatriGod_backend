package com.patrigod.article.application;

import com.patrigod.article.domain.entity.Article;

import java.util.List;

public interface GetAllArticleUseCase {
    List<Article> getAllArticle();
}
