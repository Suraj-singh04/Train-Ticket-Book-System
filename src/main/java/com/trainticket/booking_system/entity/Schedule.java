package com.trainticket.booking_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @Column(nullable = false)
    private LocalTime departureTime;

    @Column(nullable = false)
    private LocalTime arrivalTime;

    @Column(nullable = false)
    private Integer journeyDurationMinutes;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "schedule_days",
            joinColumns = @JoinColumn(name = "schedule_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private Set<DayOfWeek> daysOfOperation = new HashSet<>();

    @Column(nullable = false)
    private boolean active = true;

    protected Schedule() {}

     public Schedule(
            Train train,
            Route route,
            LocalTime departureTime,
            LocalTime arrivalTime,
            Integer journeyDurationMinutes,
            Set<DayOfWeek> daysOfOperation
    ) {
        this.train = train;
        this.route = route;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.journeyDurationMinutes = journeyDurationMinutes;
        this.daysOfOperation = daysOfOperation;
    }
}