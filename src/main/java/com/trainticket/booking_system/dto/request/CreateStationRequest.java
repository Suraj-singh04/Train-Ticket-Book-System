package com.trainticket.booking_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class CreateStationRequest {
    
    @NotBlank(message = "Station code is required")
    @Size(min = 2, max = 10)
    private String stationCode;

    @NotBlank(message = "Station name is required")
    @Size(min = 2, max = 100)
    private String stationName;
}
