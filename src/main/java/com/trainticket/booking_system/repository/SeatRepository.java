package com.trainticket.booking_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainticket.booking_system.entity.Coach;
import com.trainticket.booking_system.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, String> {

    List<Seat> findByCoachOrderBySeatNumberAsc(Coach coach);

    Optional<Seat> findFirstByCoachAndAvailableTrueOrderBySeatNumberAsc(Coach coach);

}