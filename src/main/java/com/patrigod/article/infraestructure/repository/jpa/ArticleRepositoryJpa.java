package com.patrigod.article.infraestructure.repository.jpa;

import com.patrigod.article.infraestructure.repository.jpa.entity.ArticleJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepositoryJpa extends JpaRepository<ArticleJpa,Long>{
    
}
