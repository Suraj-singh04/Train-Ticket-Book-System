package com.trainticket.booking_system.dto.request;


import com.trainticket.booking_system.entity.CoachType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCoachRequest {

    @NotBlank(message = "Train ID is required")
    private String trainId;

    @NotBlank(message = "Coach number is required")
    private String coachNumber;

    @NotNull(message = "Coach type is required")
    private CoachType coachType;

    @NotNull(message = "Total seats are required")
    @Min(value = 1, message = "Total seats must be greater than 0")
    private Integer totalSeats;
}