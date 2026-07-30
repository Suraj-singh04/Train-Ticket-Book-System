package com.trainticket.booking_system.dto.response;

import com.trainticket.booking_system.entity.CoachType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoachResponse {

    private String coachId;

    private String trainId;

    private String trainNumber;

    private String coachNumber;

    private CoachType coachType;

    private Integer totalSeats;

    private Boolean active;
}