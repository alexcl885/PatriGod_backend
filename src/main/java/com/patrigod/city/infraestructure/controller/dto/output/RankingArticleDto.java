package com.patrigod.city.infraestructure.controller.dto.output;

public interface RankingArticleDto {

    Integer getPosition();

    Long getCityId();

    String getCityName();

    Double getAverageScore();

}
