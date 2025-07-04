package com.patrigod.article.application.mapper;

import com.patrigod.article.domain.entity.Article;
import com.patrigod.article.infraestructure.controller.dto.output.ArticleOutputDto;
import com.patrigod.article.infraestructure.repository.jpa.entity.ArticleJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleJpa toJpa(Article article);

    Article toModel(ArticleJpa articleJpa);

    ArticleOutputDto toOutputDto(Article article);

}
