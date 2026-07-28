package com.trainticket.booking_system.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateRouteRequest {
    
    @NotEmpty(message = "Route must contain at least two station")
    @Valid
    private List<RouteStationRequest> stations;
}
