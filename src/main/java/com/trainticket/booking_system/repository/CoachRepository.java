package com.trainticket.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainticket.booking_system.entity.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    
}
