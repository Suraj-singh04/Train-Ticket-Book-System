package com.trainticket.booking_system.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.trainticket.booking_system.entity.BookingStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponse {

    private String bookingId;

    private String bookingReference;

    private String passengerName;

    private String trainNumber;

    private String coachNumber;

    private Integer seatNumber;

    private LocalDate travelDate;

    private Double fare;

    private BookingStatus bookingStatus;

    private LocalDateTime bookingTime;
}