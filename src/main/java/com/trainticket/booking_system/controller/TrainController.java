package com.trainticket.booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainticket.booking_system.dto.request.CreateTrainRequest;
import com.trainticket.booking_system.dto.response.TrainResponse;
import com.trainticket.booking_system.service.TrainService;

@RestController
@RequestMapping("/api/trains")
public class TrainController {
    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping
    public ResponseEntity<TrainResponse> createTrain(@RequestBody CreateTrainRequest request) {
        TrainResponse response = trainService.createTrain(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TrainResponse>> getAllTrains() {
        
        return ResponseEntity.ok(
            trainService.getAllTrains()
        );
    }
}
