package com.patrigod.rating.infrastructure.controller.dto.input;

import com.patrigod.article.infraestructure.controller.dto.output.ArticleOutputDto;
import com.patrigod.usuario.entity.entity.Usuario;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingInputDto {

    private Long id;

    private Usuario user;

    private ArticleOutputDto article;

    private Float rating;
}
