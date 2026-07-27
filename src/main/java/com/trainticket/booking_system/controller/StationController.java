package com.trainticket.booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainticket.booking_system.dto.request.CreateStationRequest;
import com.trainticket.booking_system.dto.response.StationResponse;
import com.trainticket.booking_system.service.StationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stations")
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping
    public ResponseEntity<StationResponse> createStation(@Valid @RequestBody CreateStationRequest request) {
        StationResponse response = stationService.createStation(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StationResponse>> getAllStations() {

        return ResponseEntity.ok(
            stationService.getAllStations()
        );
    }

    @GetMapping("/{stationId}")
    public ResponseEntity<StationResponse> getStationById(@PathVariable String stationId) {
        StationResponse response = stationService.getStationById(stationId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{stationId}")
    public ResponseEntity<StationResponse> updateStation(@PathVariable String stationId, @RequestBody CreateStationRequest request) {
        StationResponse response = stationService.updateStation(stationId, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{stationId}")
    public ResponseEntity<Void> deleteStation(@PathVariable String stationId) {
        stationService.deleteStation(stationId);

        return ResponseEntity.noContent().build();
    }
}
