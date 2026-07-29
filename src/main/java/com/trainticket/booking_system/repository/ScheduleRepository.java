package com.trainticket.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trainticket.booking_system.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    
}
