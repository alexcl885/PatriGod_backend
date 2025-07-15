package com.patrigod.event.infraestructure.controller.dto.input;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.patrigod.rating.infrastructure.controller.dto.input.RatingInputDto;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "City ID is required")
    private Long cityId;

    @NotBlank(message = "Event name is required")
    @Size(max = 100, message = "Event name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    private List<RatingInputDto> ratings = new ArrayList<>();

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date must be today or in the future")
    private LocalDate date;

    @NotNull(message = "Event time is required")
    private LocalTime eventTime;

    @NotBlank(message = "Location is required")
    @Size(max = 255, message = "Location cannot exceed 255 characters")
    private String location;

    @Size(max = 1000, message = "Event information cannot exceed 1000 characters")
    private String eventInformation;

    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    @Pattern(regexp = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|jpeg|gif|png|svg)",
            message = "Image must be a valid URL ending with jpg, jpeg, gif, png or svg")
    private String image;

    @NotBlank(message = "Event type is required")
    @Size(max = 50, message = "Event type cannot exceed 50 characters")
    private String eventType;

    @Size(max = 100, message = "Organizer cannot exceed 100 characters")
    private String organizer;

    @Size(max = 255, message = "Official website URL cannot exceed 255 characters")
    @Pattern(regexp = "^(https?://)?(www\\.)?[\\w\\-]+(\\.[\\w\\-]+)+[/#?]?.*$",
            message = "Official website must be a valid URL")
    private String officialWebsite;

    @Size(max = 50, message = "Price cannot exceed 50 characters")
    private String price;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Max(value = 1440, message = "Duration cannot exceed 1440 minutes (24 hours)")
    private Integer duration;

    @Size(max = 50, message = "Type cannot exceed 50 characters")
    private String type;


}
