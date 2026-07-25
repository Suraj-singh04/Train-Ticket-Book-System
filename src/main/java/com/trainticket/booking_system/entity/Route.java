package com.trainticket.booking_system.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String routeId;

    @Column(nullable = false)
    private String originStation;

    @Column(nullable = false)
    private String destinationStation;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("stationOrder ASC")
    private List<RouteStation> orderedStations = new ArrayList<>();

    protected Route() {}

    public Route(String originStation, String destinationStation) {
        this.originStation = originStation;
        this.destinationStation = destinationStation;
    }

    public void addStation(RouteStation station) {
        orderedStations.add(station);
        station.setRoute(this);
    }

    public String getRouteId() { return routeId; }
    public String getOriginStation() { return originStation; }
    public String getDestinationStation() { return destinationStation; }
    public List<RouteStation> getOrderedStations() { return orderedStations; }
}
