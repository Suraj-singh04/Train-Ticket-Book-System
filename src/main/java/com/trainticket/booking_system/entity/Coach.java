package com.trainticket.booking_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "coaches",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"train_id", "coach_number"})
}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String coachId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @Column(name = "coach_number", nullable = false)
    private String coachNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CoachType coachType;

    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Boolean active = true;

    public Coach(Train train,
                 String coachNumber,
                 CoachType coachType,
                 Integer totalSeats) {

        this.train = train;
        this.coachNumber = coachNumber;
        this.coachType = coachType;
        this.totalSeats = totalSeats;
        this.active = true;
    }

}