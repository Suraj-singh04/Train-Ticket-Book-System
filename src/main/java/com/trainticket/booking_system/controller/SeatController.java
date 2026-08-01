package com.trainticket.booking_system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainticket.booking_system.dto.response.SeatResponse;
import com.trainticket.booking_system.service.SeatService;

@RestController
@RequestMapping("/api")
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/coaches/{coachId}/seats")
    public ResponseEntity<List<SeatResponse>> getSeatsByCoach(
            @PathVariable String coachId) {

        return ResponseEntity.ok(
                seatService.getSeatsByCoach(coachId));
    }

    @GetMapping("/seats/{seatId}")
    public ResponseEntity<SeatResponse> getSeatById(
            @PathVariable String seatId) {

        return ResponseEntity.ok(
                seatService.getSeatById(seatId));
    }
}
