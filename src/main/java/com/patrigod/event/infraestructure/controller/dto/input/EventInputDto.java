package com.patrigod.event.infraestructure.controller.dto.input;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.patrigod.rating.infrastructure.controller.dto.input.RatingInputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventInputDto {
    private Long id;

    private Long cityId;

    private String name;

    private String description;

    private List<RatingInputDto> ratings = new ArrayList<>();

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

    private String type;
}
