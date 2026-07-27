package com.trainticket.booking_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainticket.booking_system.entity.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, String> {
    
    Optional<Station> findByStationCode(String stationCode);

    boolean existsByStationCode(String stationCode);
}
