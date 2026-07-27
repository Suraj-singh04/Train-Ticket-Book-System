package com.trainticket.booking_system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trainticket.booking_system.dto.request.CreateStationRequest;
import com.trainticket.booking_system.dto.response.StationResponse;
import com.trainticket.booking_system.entity.Station;
import com.trainticket.booking_system.exception.DuplicateResourceException;
import com.trainticket.booking_system.exception.ResourceNotFoundException;
import com.trainticket.booking_system.repository.StationRepository;
import com.trainticket.booking_system.service.StationService;

@Service
public class StationServiceImpl implements StationService {
    
    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    private StationResponse mapToResponse(Station station) {
    return new StationResponse(
            station.getStationId(),
            station.getStationCode(),
            station.getStationName()
    );
}

    @Override
    public StationResponse createStation(CreateStationRequest request) {
        if(stationRepository.existsByStationCode(request.getStationCode())) {
            throw new DuplicateResourceException("Station with code "+ request.getStationCode()+" already exists");
        }
        
        Station station = new Station(
            request.getStationCode(),
            request.getStationName()
        );

        Station savedStation = stationRepository.save(station);
        
        return mapToResponse(savedStation);
    }

    @Override
    public List<StationResponse> getAllStations() {
        List<Station> stations = stationRepository.findAll();

        return stations.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public StationResponse getStationById(String stationId) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new ResourceNotFoundException("Station not found with id: " + stationId));

        return mapToResponse(station);
    }

    @Override
    public StationResponse updateStation(String stationId, CreateStationRequest request) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new ResourceNotFoundException("Station not found with id: " + stationId));

        if (!station.getStationCode().equals(request.getStationCode())
                && stationRepository.existsByStationCode(request.getStationCode())) {

            throw new DuplicateResourceException(
                    "Station with code " + request.getStationCode() + " already exists");
        }
        station.setStationCode(request.getStationCode());
        station.setStationName(request.getStationName());

        Station updatedStation = stationRepository.save(station);

        return mapToResponse(updatedStation);
    }

    @Override
    public void deleteStation(String stationId) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new ResourceNotFoundException("Station not found with id: " + stationId));
        
        stationRepository.delete(station);
    }

}