package com.trainticket.booking_system.service;

import java.util.List;

import com.trainticket.booking_system.dto.request.CreatePassengerRequest;
import com.trainticket.booking_system.dto.response.PassengerResponse;

public interface PassengerService {

    PassengerResponse createPassenger(CreatePassengerRequest request);

    List<PassengerResponse> getAllPassengers();

    PassengerResponse getPassengerById(String passengerId);

    PassengerResponse updatePassenger(
            String passengerId,
            CreatePassengerRequest request);

    void deletePassenger(String passengerId);
}