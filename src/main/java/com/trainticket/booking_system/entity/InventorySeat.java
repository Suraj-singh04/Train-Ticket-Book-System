package com.trainticket.booking_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventory_seats")
public class InventorySeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(nullable = false)
    private String coachLabel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat physicalSeat;

    @Column(nullable = false)
    private Integer occupancyBitmask = 0; 

    @Version // Optimistic locking to prevent double-booking at database level completely!
    private Long version;

    protected InventorySeat() {}

    public synchronized boolean reserveSegments(int segmentMask) { 
        if ((this.occupancyBitmask & segmentMask) != 0) {
             return false; 
        }

        this.occupancyBitmask |= segmentMask;
        return true;
    }
}
