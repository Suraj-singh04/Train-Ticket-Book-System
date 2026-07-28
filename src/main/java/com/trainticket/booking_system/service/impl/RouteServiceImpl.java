package com.trainticket.booking_system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trainticket.booking_system.dto.request.CreateRouteRequest;
import com.trainticket.booking_system.dto.request.RouteStationRequest;
import com.trainticket.booking_system.dto.response.RouteResponse;
import com.trainticket.booking_system.dto.response.RouteStationResponse;
import com.trainticket.booking_system.entity.Route;
import com.trainticket.booking_system.entity.RouteStation;
import com.trainticket.booking_system.entity.Station;
import com.trainticket.booking_system.exception.ResourceNotFoundException;
import com.trainticket.booking_system.repository.RouteRepository;
import com.trainticket.booking_system.repository.StationRepository;
import com.trainticket.booking_system.service.RouteService;


@Service
@Transactional
public class RouteServiceImpl implements RouteService {
    
    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;

    public RouteServiceImpl(RouteRepository routeRepository, StationRepository stationRepository) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
    }

    private RouteResponse mapToRouteResponse(Route route) {
        List<RouteStationResponse> stationResponses = route.getOrderedStations()
            .stream()
            .map(rs -> new RouteStationResponse(
                rs.getStation().getStationId(),
                rs.getStation().getStationCode(),
                rs.getStation().getStationName(),
                rs.getStopOrder(),
                rs.getDistanceFromOrigin()
            ))
            .toList();
            
        return new RouteResponse(
            route.getRouteId(),
            route.getOriginStation().getStationId(),
            route.getDestinationStation().getStationId(),
            stationResponses
        );
    }

    @Override
    public RouteResponse createRoute(CreateRouteRequest request) {
        if (request.getStations().size() < 2) {
            throw new IllegalArgumentException("A route must contain at least two stations.");
        }

        RouteStationRequest originRequest = request.getStations().get(0);
        RouteStationRequest destinationRequest = request.getStations().get(request.getStations().size() - 1);

        Station originStation = stationRepository.findById(originRequest.getStationId())
            .orElseThrow(() -> new ResourceNotFoundException("Origin station not found with id: " + originRequest.getStationId()));

        Station destinationStation = stationRepository.findById(destinationRequest.getStationId())
            .orElseThrow(() -> new ResourceNotFoundException("Destination station not found with id: " + destinationRequest.getStationId()));
    
        Route route = new Route(originStation, destinationStation);

        for(int i=0; i<request.getStations().size(); i++) {
            
            RouteStationRequest stationRequest = request.getStations().get(i);
            
            Station station = stationRepository.findById(stationRequest.getStationId())
                .orElseThrow(() -> new ResourceNotFoundException("Station not found with id: " + stationRequest.getStationId()));
            
            RouteStation routeStation = new RouteStation(
                station,
                i+1,
                stationRequest.getDistanceFromOrigin()
            );
            route.addStation(routeStation);
        }

        Route savedRoute = routeRepository.save(route);

        return mapToRouteResponse(savedRoute);
    }

    @Override
    public List<RouteResponse> getAllRoutes() {
        List<Route> routes = routeRepository.findAll();
        return routes.stream()
            .map(this::mapToRouteResponse)
            .toList();
    }
    
    @Override
    public RouteResponse getRouteById(String routeId) {
        Route route = routeRepository.findById(routeId)
            .orElseThrow(() -> new ResourceNotFoundException("Route not found with id: " + routeId));
        return mapToRouteResponse(route);
    }

    @Override
    public void deleteRoute(String routeId) {
        Route route = routeRepository.findById(routeId)
            .orElseThrow(() -> new ResourceNotFoundException("Route not found with id: " + routeId));
        routeRepository.delete(route);
    }
}