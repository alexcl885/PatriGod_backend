package com.patrigod.rating.domain.entity;

import com.patrigod.article.domain.entity.Article;
import com.patrigod.user.infrastructure.repository.jpa.entity.UserJpa;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    private Long id;

    private UserJpa user;

    private Article article;

    private Float rating;

}
