package com.trainticket.booking_system.service;

import java.util.List;

import com.trainticket.booking_system.dto.request.CreateCoachRequest;
import com.trainticket.booking_system.dto.response.CoachResponse;

public interface CoachService {
    CoachResponse createCoach(CreateCoachRequest request);

    List<CoachResponse> getAllCoaches();

    CoachResponse getCoachById(String coachId);

    CoachResponse updateCoach(String coachId, CreateCoachRequest request);

    void deleteCoach(String coachId);
}
