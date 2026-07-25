package com.trainticket.booking_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // PNR
    private String bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(nullable = false)
    private String fromStationCode;

    @Column(nullable = false)
    private String toStationCode;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketReservation> reservedSeats = new ArrayList<>();

    @Column(nullable = false, precision = 10, scale = 2) // Good precision mapping for Postgres numeric type
    private BigDecimal totalFare;

    @Column(nullable = false)
    private LocalDateTime bookingTimestamp;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public enum BookingStatus { CONFIRMED, CANCELLED, FAILED }

    protected Booking() {}

    @PrePersist
    protected void onBooking() {
        this.bookingTimestamp = LocalDateTime.now();
    }
}
