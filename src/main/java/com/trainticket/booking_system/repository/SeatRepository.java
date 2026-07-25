package com.trainticket.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainticket.booking_system.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
