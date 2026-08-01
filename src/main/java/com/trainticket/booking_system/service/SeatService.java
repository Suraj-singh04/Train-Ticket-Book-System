package com.trainticket.booking_system.service;

import java.util.List;

import com.trainticket.booking_system.dto.response.SeatResponse;

public interface SeatService {

    List<SeatResponse> getSeatsByCoach(String coachId);

    SeatResponse getSeatById(String seatId);

}