package com.trainticket.booking_system.service.impl;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trainticket.booking_system.dto.request.CreateScheduleRequest;
import com.trainticket.booking_system.dto.response.ScheduleResponse;
import com.trainticket.booking_system.entity.Route;
import com.trainticket.booking_system.entity.Schedule;
import com.trainticket.booking_system.entity.Train;
import com.trainticket.booking_system.exception.ResourceNotFoundException;
import com.trainticket.booking_system.repository.RouteRepository;
import com.trainticket.booking_system.repository.ScheduleRepository;
import com.trainticket.booking_system.repository.TrainRepository;
import com.trainticket.booking_system.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    
    private final ScheduleRepository scheduleRepository;
    private final TrainRepository trainRepository;
    private final RouteRepository routeRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, TrainRepository trainRepository, RouteRepository routeRepository) {
        this.scheduleRepository = scheduleRepository;
        this.trainRepository = trainRepository;
        this.routeRepository = routeRepository;
    }

    private int calculateJourneyDuration(LocalTime departure,LocalTime arrival) {

        int departureMinutes = departure.toSecondOfDay() / 60;
        int arrivalMinutes = arrival.toSecondOfDay() / 60;

        if (arrivalMinutes < departureMinutes) {
            arrivalMinutes += 24 * 60;
        }

        return arrivalMinutes - departureMinutes;
    }

    private ScheduleResponse mapToScheduleResponse(Schedule schedule) {
        ScheduleResponse response = new ScheduleResponse();
        response.setScheduleId(schedule.getScheduleId());

        response.setTrainId(schedule.getTrain().getTrainId());
        response.setTrainNumber(schedule.getTrain().getTrainNumber());
        response.setTrainName(schedule.getTrain().getTrainName());

        response.setRouteId(schedule.getRoute().getRouteId());
        response.setDepartureTime(schedule.getDepartureTime());
        response.setArrivalTime(schedule.getArrivalTime());
        response.setJourneyDurationMinutes(schedule.getJourneyDurationMinutes());

        response.setDaysOfOperation(schedule.getDaysOfOperation());
        response.setActive(schedule.isActive());

        return response;
    }

    @Override
    public ScheduleResponse createSchedule(CreateScheduleRequest request) {
        Train train = trainRepository.findById(request.getTrainId())
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with ID: " + request.getTrainId()));
        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with ID: " + request.getRouteId()));

        int duration = calculateJourneyDuration(
            request.getDepartureTime(), 
            request.getArrivalTime()
        );

        Schedule schedule = new Schedule(
            train,
            route,
            request.getDepartureTime(),
            request.getArrivalTime(),
            duration,
            request.getDaysOfOperation()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return mapToScheduleResponse(savedSchedule);
    }

    @Override
    public List<ScheduleResponse> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(this::mapToScheduleResponse)
                .toList();
    }

    @Override
    public ScheduleResponse getScheduleById(String scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with ID: " + scheduleId));
        return mapToScheduleResponse(schedule);
    }

    @Override
    public ScheduleResponse updateSchedule(String scheduleId, CreateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with ID: " + scheduleId));
        
        Train train = trainRepository.findById(request.getTrainId())
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with ID: " + request.getTrainId()));
        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with ID: " + request.getRouteId()));
        
        int duration = calculateJourneyDuration(
            request.getDepartureTime(), 
            request.getArrivalTime()
        );

        schedule.setTrain(train);
        schedule.setRoute(route);
        schedule.setDepartureTime(request.getDepartureTime());
        schedule.setArrivalTime(request.getArrivalTime());
        schedule.setJourneyDurationMinutes(duration);
        schedule.setDaysOfOperation(request.getDaysOfOperation());

        Schedule updatedSchedule = scheduleRepository.save(schedule);
        
        return mapToScheduleResponse(updatedSchedule);
    }

    @Override
    public void deleteSchedule(String scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with ID: " + scheduleId));
        scheduleRepository.delete(schedule);
    }
}
