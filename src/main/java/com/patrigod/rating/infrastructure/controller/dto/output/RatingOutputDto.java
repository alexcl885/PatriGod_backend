package com.patrigod.rating.infrastructure.controller.dto.output;

import com.patrigod.article.infraestructure.controller.dto.output.ArticleOutputDto;
import com.patrigod.user.infrastructure.repository.jpa.entity.UserJpa;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingOutputDto {

    private Long id;

    private UserJpa user;

    private ArticleOutputDto article;

    private Float rating;

}
