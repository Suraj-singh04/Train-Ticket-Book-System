package com.trainticket.booking_system.dto.response;

import lombok.Getter;

@Getter
public class StationResponse {

    private String stationId;
    private String stationCode;
    private String stationName;

    public StationResponse(String stationId, String stationCode, String stationName) {
        this.stationId = stationId;
        this.stationCode = stationCode;
        this.stationName = stationName;
    }
    
}
