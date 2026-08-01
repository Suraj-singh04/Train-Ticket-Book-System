package com.trainticket.booking_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"coach_id", "seat_number"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String seatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;

    @Column(nullable = false)
    private Boolean available = true;

    public Seat(Coach coach,
                Integer seatNumber,
                SeatType seatType) {

        this.coach = coach;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.available = true;
    }
}