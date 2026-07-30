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

import com.trainticket.booking_system.dto.request.CreateCoachRequest;
import com.trainticket.booking_system.dto.response.CoachResponse;
import com.trainticket.booking_system.service.CoachService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {
    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping
    public ResponseEntity<CoachResponse> createCoach(@Valid @RequestBody CreateCoachRequest request) {
        CoachResponse response = coachService.createCoach(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CoachResponse>> getAllCoaches() {
        List<CoachResponse> coaches = coachService.getAllCoaches();
        return ResponseEntity.ok(coaches);
    }

    @PutMapping("/{coachId}")
    public ResponseEntity<CoachResponse> updateCoach(@PathVariable String coachId, @Valid @RequestBody CreateCoachRequest request) {
        CoachResponse response = coachService.updateCoach(coachId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{coachId}")
    public ResponseEntity<CoachResponse> getCoachById(@PathVariable String coachId) {
        CoachResponse response = coachService.getCoachById(coachId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{coachId}")
    public ResponseEntity<Void> deleteCoach(@PathVariable String coachId) {
        coachService.deleteCoach(coachId);
        return ResponseEntity.noContent().build();
    }
}
