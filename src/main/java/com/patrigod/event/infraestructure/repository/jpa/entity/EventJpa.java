package com.patrigod.event.infraestructure.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.patrigod.article.infraestructure.repository.jpa.entity.ArticleJpa;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder


@PrimaryKeyJoinColumn(name = "id") // Event class inherits from Article id
@JsonTypeName("event")

@Table(name = "event")
public class EventJpa extends ArticleJpa {

    private LocalDate date;

    private LocalTime eventTime;

    private String location;

    @Column(length = 1000)
    private String eventInformation;

    private String image;

    private String eventType;

    private String organizer;

    private String officialWebsite;

    private String price;

    private Integer duration;
}
