package com.trainticket.booking_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String seatId;

    @ManyToOne
    @JoinColumn(name="coach_id")
    private Coach coach;

    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    private BerthType berthType;

    protected Seat(){}
}

enum BerthType {
    LOWER, MIDDLE, UPPER, SIDE_LOWER, SIDE_UPPER
}