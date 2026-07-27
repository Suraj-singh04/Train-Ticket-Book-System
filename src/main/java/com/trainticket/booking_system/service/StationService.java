package com.trainticket.booking_system.service;

import java.util.List;

import com.trainticket.booking_system.dto.request.CreateStationRequest;
import com.trainticket.booking_system.dto.response.StationResponse;

public interface StationService {
    
    StationResponse createStation(CreateStationRequest request);

    List<StationResponse> getAllStations();

    StationResponse getStationById(String stationId);

    StationResponse updateStation(String stationId, CreateStationRequest request);

    void deleteStation(String stationId);
}
