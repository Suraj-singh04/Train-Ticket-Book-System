package com.trainticket.booking_system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trainticket.booking_system.dto.request.CreatePassengerRequest;
import com.trainticket.booking_system.dto.response.PassengerResponse;
import com.trainticket.booking_system.entity.Passenger;
import com.trainticket.booking_system.exception.DuplicateResourceException;
import com.trainticket.booking_system.exception.ResourceNotFoundException;
import com.trainticket.booking_system.repository.PassengerRepository;
import com.trainticket.booking_system.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    private PassengerResponse mapToPassengerResponse(Passenger passenger) {

        PassengerResponse response = new PassengerResponse();

        response.setPassengerId(passenger.getPassengerId());
        response.setFullName(passenger.getFullName());
        response.setAge(passenger.getAge());
        response.setGender(passenger.getGender());
        response.setEmail(passenger.getEmail());
        response.setPhoneNumber(passenger.getPhoneNumber());
        response.setIdentityType(passenger.getIdentityType());
        response.setIdentityNumber(passenger.getIdentityNumber());

        return response;
    }

    @Override
    public PassengerResponse createPassenger(CreatePassengerRequest request) {

        if (passengerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "Passenger with email " + request.getEmail() + " already exists");
        }

        if (passengerRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateResourceException(
                    "Passenger with phone number " + request.getPhoneNumber() + " already exists");
        }

        if (passengerRepository.existsByIdentityNumber(request.getIdentityNumber())) {
            throw new DuplicateResourceException(
                    "Passenger with identity number " + request.getIdentityNumber() + " already exists");
        }

        Passenger passenger = new Passenger(
                request.getFullName(),
                request.getAge(),
                request.getGender(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getIdentityType(),
                request.getIdentityNumber());

        Passenger savedPassenger = passengerRepository.save(passenger);

        return mapToPassengerResponse(savedPassenger);
    }

    @Override
    public List<PassengerResponse> getAllPassengers() {

        return passengerRepository.findAll()
                .stream()
                .map(this::mapToPassengerResponse)
                .toList();
    }

    @Override
    public PassengerResponse getPassengerById(String passengerId) {

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Passenger not found with id: " + passengerId));

        return mapToPassengerResponse(passenger);
    }

    @Override
    public PassengerResponse updatePassenger(
            String passengerId,
            CreatePassengerRequest request) {

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Passenger not found with id: " + passengerId));

        if (!passenger.getEmail().equals(request.getEmail())
                && passengerRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException(
                    "Passenger with email " + request.getEmail() + " already exists");
        }

        if (!passenger.getPhoneNumber().equals(request.getPhoneNumber())
                && passengerRepository.existsByPhoneNumber(request.getPhoneNumber())) {

            throw new DuplicateResourceException(
                    "Passenger with phone number " + request.getPhoneNumber() + " already exists");
        }

        if (!passenger.getIdentityNumber().equals(request.getIdentityNumber())
                && passengerRepository.existsByIdentityNumber(request.getIdentityNumber())) {

            throw new DuplicateResourceException(
                    "Passenger with identity number " + request.getIdentityNumber() + " already exists");
        }

        passenger.setFullName(request.getFullName());
        passenger.setAge(request.getAge());
        passenger.setGender(request.getGender());
        passenger.setEmail(request.getEmail());
        passenger.setPhoneNumber(request.getPhoneNumber());
        passenger.setIdentityType(request.getIdentityType());
        passenger.setIdentityNumber(request.getIdentityNumber());

        Passenger updatedPassenger = passengerRepository.save(passenger);

        return mapToPassengerResponse(updatedPassenger);
    }

    @Override
    public void deletePassenger(String passengerId) {

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Passenger not found with id: " + passengerId));

        passengerRepository.delete(passenger);
    }

}