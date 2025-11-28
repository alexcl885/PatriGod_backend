package com.patrigod.rating.domain.entity;

import com.patrigod.article.domain.entity.Article;
import com.patrigod.user.domain.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    private Long id;

    private User user;

    private Article article;

    private Float rating;

}
