package com.patrigod.city.infraestructure.controller.dto.output;


public interface RankingCityDto {

    Integer getPosition();

    Long getCityId();

    String getCityName();

    Double getAverageRating();

}