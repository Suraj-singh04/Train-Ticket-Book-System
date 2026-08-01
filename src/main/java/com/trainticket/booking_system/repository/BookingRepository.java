package com.trainticket.booking_system.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trainticket.booking_system.entity.Booking;
import com.trainticket.booking_system.entity.Schedule;
import com.trainticket.booking_system.entity.Seat;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
        SELECT b.seat
        FROM Booking b
        WHERE b.schedule = :schedule
        AND b.travelDate = :travelDate
        AND b.bookingStatus = 'CONFIRMED'
    """)
    List<Seat> findBookedSeats(
            @Param("schedule") Schedule schedule,
            @Param("travelDate") LocalDate travelDate);
}
