package com.trainticket.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainticket.booking_system.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    
}
