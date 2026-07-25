package com.trainticket.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainticket.booking_system.entity.RouteStation;

public interface RouteStationRepository extends JpaRepository<RouteStation, Long> {
    
}
