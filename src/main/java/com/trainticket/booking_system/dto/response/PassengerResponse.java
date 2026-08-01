package com.trainticket.booking_system.dto.response;

import com.trainticket.booking_system.entity.Gender;
import com.trainticket.booking_system.entity.IdentityType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerResponse {

    private String passengerId;

    private String fullName;

    private Integer age;

    private Gender gender;

    private String email;

    private String phoneNumber;

    private IdentityType identityType;

    private String identityNumber;
}