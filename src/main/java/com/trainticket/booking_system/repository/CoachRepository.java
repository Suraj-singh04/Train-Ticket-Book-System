package com.trainticket.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainticket.booking_system.entity.Coach;
import com.trainticket.booking_system.entity.Train;

public interface CoachRepository extends JpaRepository<Coach, String> {

    boolean existsByTrainAndCoachNumber(Train train, String coachNumber);
    boolean existsByTrainAndCoachNumberAndCoachIdNot(
        Train train,
        String coachNumber,
        String coachId
);
}
