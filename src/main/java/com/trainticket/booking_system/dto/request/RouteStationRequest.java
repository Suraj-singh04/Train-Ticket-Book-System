package com.trainticket.booking_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RouteStationRequest {

    @NotBlank(message = "Station ID is required")
    private String stationId;

    @PositiveOrZero(message = "Distance must be a non-negative value")
    private int distanceFromOrigin;

}
