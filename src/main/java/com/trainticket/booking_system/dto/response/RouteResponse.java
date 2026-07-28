package com.trainticket.booking_system.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteResponse {

    private String routeId;
    private String originStation;
    private String destinationStation;
    private List<RouteStationResponse> stations;

    public RouteResponse(String routeId, String originStation, String destinationStation, List<RouteStationResponse> stations) {
        this.routeId = routeId;
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.stations = stations;
    }
}