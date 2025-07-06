package com.patrigod.event.infraestructure.controller.dto.output;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.patrigod.city.infraestructure.controller.dto.output.CityOutputDto;
import com.patrigod.rating.infrastructure.controller.dto.output.RatingOutputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventOutputDto {

    private Long id;

    private CityOutputDto city;

    private String name;

    private List<RatingOutputDto> ratings = new ArrayList<>();

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
