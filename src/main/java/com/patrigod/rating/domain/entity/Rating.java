package com.patrigod.rating.domain.entity;

import com.patrigod.article.domain.entity.Article;
import com.patrigod.usuario.entity.entity.Usuario;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    private Long id;

    private Usuario user;

    private Article article;

    private Float rating;

}
