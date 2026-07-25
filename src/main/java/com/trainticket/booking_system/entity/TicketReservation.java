package com.trainticket.booking_system.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class TicketReservation {
    private String passengerName;
    private int passengerAge;
    private String assignedCoachLabel;
    private int assignedSeatNumber;

    public TicketReservation(String passengerName, int passengerAge, String assignedCoachLabel, int assignedSeatNumber) {
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
        this.assignedCoachLabel = assignedCoachLabel;
        this.assignedSeatNumber = assignedSeatNumber;
    }
}
