package com.trainticket.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainticket.booking_system.entity.Train;

public interface TrainRepository extends JpaRepository<Train, String> {
    
}
