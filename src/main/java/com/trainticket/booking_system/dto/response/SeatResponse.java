package com.trainticket.booking_system.dto.response;

import com.trainticket.booking_system.entity.SeatType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatResponse {

    private String seatId;

    private String coachId;

    private String coachNumber;

    private Integer seatNumber;

    private SeatType seatType;
}