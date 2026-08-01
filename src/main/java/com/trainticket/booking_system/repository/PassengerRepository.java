package com.trainticket.booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainticket.booking_system.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, String> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByIdentityNumber(String identityNumber);
}
