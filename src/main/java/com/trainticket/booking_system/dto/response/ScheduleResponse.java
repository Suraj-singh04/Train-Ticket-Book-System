package com.trainticket.booking_system.dto.response;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse{

        String scheduleId;

        String trainId;
        String trainNumber;
        String trainName;

        String routeId; 

        LocalTime departureTime;
        LocalTime arrivalTime;

        Integer journeyDurationMinutes;

        Set<DayOfWeek> daysOfOperation;

        boolean active;

}