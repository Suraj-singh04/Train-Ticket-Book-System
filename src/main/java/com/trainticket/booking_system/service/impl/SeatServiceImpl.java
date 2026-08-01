package com.trainticket.booking_system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trainticket.booking_system.dto.response.SeatResponse;
import com.trainticket.booking_system.entity.Coach;
import com.trainticket.booking_system.entity.Seat;
import com.trainticket.booking_system.exception.ResourceNotFoundException;
import com.trainticket.booking_system.repository.CoachRepository;
import com.trainticket.booking_system.repository.SeatRepository;
import com.trainticket.booking_system.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final CoachRepository coachRepository;

    public SeatServiceImpl(SeatRepository seatRepository, CoachRepository coachRepository) {
        this.seatRepository = seatRepository;
        this.coachRepository = coachRepository;
    }

    @Override
    public List<SeatResponse> getSeatsByCoach(String coachId) {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Coach not found"));

        return seatRepository.findByCoach(coach)
                .stream()
                .map(this::mapToSeatResponse)
                .toList();
    }

    @Override
    public SeatResponse getSeatById(String seatId) {

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Seat not found"));

        return mapToSeatResponse(seat);
    }

    private SeatResponse mapToSeatResponse(Seat seat) {

        SeatResponse response = new SeatResponse();

        response.setSeatId(seat.getSeatId());
        response.setCoachId(seat.getCoach().getCoachId());
        response.setCoachNumber(seat.getCoach().getCoachNumber());
        response.setSeatNumber(seat.getSeatNumber());
        response.setSeatType(seat.getSeatType());
        response.setAvailable(seat.getAvailable());

        return response;
    }
}
