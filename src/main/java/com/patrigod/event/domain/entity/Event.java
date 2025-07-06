package com.patrigod.event.domain.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import com.patrigod.article.domain.entity.Article;
import com.patrigod.city.domain.entity.City;
import com.patrigod.rating.domain.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Event extends Article {

    private LocalDate date;

    private LocalTime eventTime;

    private String location;

    private String eventInformation;

    private String image;

    private String eventType;

    private String organizer;

    private String officialWebsite;

    private String price;

    private Integer duration;
}
