package com.trainticket.booking_system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trainticket.booking_system.dto.request.CreateCoachRequest;
import com.trainticket.booking_system.dto.response.CoachResponse;
import com.trainticket.booking_system.entity.Coach;
import com.trainticket.booking_system.entity.Seat;
import com.trainticket.booking_system.entity.SeatType;
import com.trainticket.booking_system.entity.Train;
import com.trainticket.booking_system.exception.ResourceNotFoundException;
import com.trainticket.booking_system.repository.CoachRepository;
import com.trainticket.booking_system.repository.SeatRepository;
import com.trainticket.booking_system.repository.TrainRepository;
import com.trainticket.booking_system.service.CoachService;

@Service
public class CoachServiceImpl implements CoachService {
    private final CoachRepository coachRepository;
    private final TrainRepository trainRepository;
    private final SeatRepository seatRepository;

    public CoachServiceImpl(CoachRepository coachRepository, TrainRepository trainRepository, SeatRepository seatRepository) {
        this.coachRepository = coachRepository;
        this.trainRepository = trainRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public CoachResponse createCoach(CreateCoachRequest request) {
        Train train = trainRepository.findById(request.getTrainId())
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with ID: " + request.getTrainId()));
        
        if(coachRepository.existsByTrainAndCoachNumber(
            train, request.getCoachNumber())) {
            
            throw new IllegalArgumentException("Coach number already exists for this train");
        }

        Coach coach = new Coach(
            train,
            request.getCoachNumber(),
            request.getCoachType(),
            request.getTotalSeats()
        );

        Coach savedCoach = coachRepository.save(coach);
        generateSeats(savedCoach);

        return mapToCoachResponse(savedCoach);
    }

    @Override
    public List<CoachResponse> getAllCoaches() {
        List<Coach> coaches = coachRepository.findAll();

        return coaches.stream()
                .map(this::mapToCoachResponse)
                .toList();
    }

    @Override
    public CoachResponse getCoachById(String coachId) {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Coach not found"));

        return mapToCoachResponse(coach);
    }

    @Override
    public CoachResponse updateCoach(
            String coachId,
            CreateCoachRequest request) {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Coach not found"));

        Train train = trainRepository.findById(request.getTrainId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Train not found"));

        if (coachRepository.existsByTrainAndCoachNumberAndCoachIdNot(
                train,
                request.getCoachNumber(),
                coachId)) {

            throw new IllegalArgumentException(
                    "Coach number already exists for this train");
        }

        coach.setTrain(train);
        coach.setCoachNumber(request.getCoachNumber());
        coach.setCoachType(request.getCoachType());
        coach.setTotalSeats(request.getTotalSeats());

        Coach updatedCoach = coachRepository.save(coach);

        return mapToCoachResponse(updatedCoach);
    }

    @Override
    public void deleteCoach(String coachId) {
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Coach not found"));
        
        coachRepository.delete(coach);
    }

    
    private CoachResponse mapToCoachResponse(Coach coach) {

        CoachResponse response = new CoachResponse();

        response.setCoachId(coach.getCoachId());
        response.setTrainId(coach.getTrain().getTrainId());
        response.setTrainNumber(coach.getTrain().getTrainNumber());
        response.setCoachNumber(coach.getCoachNumber());
        response.setCoachType(coach.getCoachType());
        response.setTotalSeats(coach.getTotalSeats());
        response.setActive(coach.getActive());

        return response;
    }

    private void generateSeats(Coach coach) {

        List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= coach.getTotalSeats(); i++) {
            SeatType seatType;
            switch ((i - 1) % 5) {
                case 0:
                    seatType = SeatType.LOWER;
                    break;
                case 1:
                    seatType = SeatType.MIDDLE;
                    break;
                case 2:
                    seatType = SeatType.UPPER;
                    break;
                case 3:
                    seatType = SeatType.SIDE_LOWER;
                    break;
                default:
                    seatType = SeatType.SIDE_UPPER;
            }

            seats.add(new Seat(
                    coach,
                    i,
                    seatType
            ));
        }

        System.out.println("Seats created in memory: " + seats.size());

        seatRepository.saveAll(seats);

        System.out.println("Seats saved successfully.");
    }
}
