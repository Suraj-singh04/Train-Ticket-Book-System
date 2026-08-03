package com.trainticket.booking_system.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingRequest {

    @NotBlank(message = "Passenger ID is required")
    private String passengerId;

    @NotBlank(message = "Schedule ID is required")
    private String scheduleId;

    @NotNull(message = "Travel date is required")
    @FutureOrPresent(message = "Travel date cannot be in the past")
    private LocalDate travelDate;
}