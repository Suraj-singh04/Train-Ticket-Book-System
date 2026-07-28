package com.trainticket.booking_system.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteStationResponse {
    private String stationId;
    private String stationCode;
    private String stationName;

    private int stopOrder;
    private int distanceFromOrigin;

    public RouteStationResponse(String stationId, String stationCode, String stationName, int stopOrder, int distanceFromOrigin) {
        this.stationId = stationId;
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.stopOrder = stopOrder;
        this.distanceFromOrigin = distanceFromOrigin;
    }
}
