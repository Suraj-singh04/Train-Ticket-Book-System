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

import com.trainticket.booking_system.dto.request.CreatePassengerRequest;
import com.trainticket.booking_system.dto.response.PassengerResponse;
import com.trainticket.booking_system.service.PassengerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping
    public ResponseEntity<PassengerResponse> createPassenger(@Valid @RequestBody CreatePassengerRequest request) {
        PassengerResponse response = passengerService.createPassenger(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PassengerResponse>> getAllPassengers() {
        List<PassengerResponse> passengers = passengerService.getAllPassengers();

        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<PassengerResponse> getPassengerById(@PathVariable String passengerId) {
        PassengerResponse response = passengerService.getPassengerById(passengerId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{passengerId}")
    public ResponseEntity<PassengerResponse> updatePassenger(String passengerId, @Valid @RequestBody CreatePassengerRequest request) {
        PassengerResponse response = passengerService.updatePassenger(passengerId, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping ("/{passengerId}")
    public ResponseEntity<Void> deletePassenger(@PathVariable String passengerId) {
        passengerService.deletePassenger(passengerId);

        return ResponseEntity.noContent().build();
    }
}
