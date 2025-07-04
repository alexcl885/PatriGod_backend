package com.patrigod.article.domain.repository;

import com.patrigod.article.domain.entity.Article;

import java.util.List;

public interface GetAllArticleRepository {
    List<Article> getAllArticle();
}
