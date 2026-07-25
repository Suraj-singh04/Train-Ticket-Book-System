package com.trainticket.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainticket.booking_system.entity.InventorySeat;

public interface InventorySeatRepository extends JpaRepository<InventorySeat, Long> {
    
}
