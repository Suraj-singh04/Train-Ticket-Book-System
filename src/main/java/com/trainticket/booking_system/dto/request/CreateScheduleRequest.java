package com.trainticket.booking_system.dto.request;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateScheduleRequest {

    @NotBlank(message = "Train ID is required")
    private String trainId;

    @NotBlank(message = "Route ID is required")
    private String routeId;

    @NotNull(message = "Departure time is required")
    private LocalTime departureTime;

    @NotNull(message = "Arrival time is required")
    private LocalTime arrivalTime;

    @NotEmpty(message = "Days of operation are required")
    private Set<DayOfWeek> daysOfOperation;
}