package com.trainticket.booking_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String passengerId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdentityType identityType;

    @Column(nullable = false, unique = true)
    private String identityNumber;

    public Passenger(
            String fullName,
            Integer age,
            Gender gender,
            String email,
            String phoneNumber,
            IdentityType identityType,
            String identityNumber) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.identityType = identityType;
        this.identityNumber = identityNumber;
    }
}