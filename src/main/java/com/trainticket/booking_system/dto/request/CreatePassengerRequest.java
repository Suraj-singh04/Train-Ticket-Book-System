package com.trainticket.booking_system.dto.request;

import com.trainticket.booking_system.entity.Gender;
import com.trainticket.booking_system.entity.IdentityType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePassengerRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age must not exceed 120")
    private Integer age;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotNull(message = "Identity type is required")
    private IdentityType identityType;

    @NotBlank(message = "Identity number is required")
    private String identityNumber;
}