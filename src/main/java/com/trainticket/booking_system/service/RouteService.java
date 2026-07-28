package com.trainticket.booking_system.service;

import java.util.List;

import com.trainticket.booking_system.dto.request.CreateRouteRequest;
import com.trainticket.booking_system.dto.response.RouteResponse;

public interface RouteService {
    RouteResponse createRoute(CreateRouteRequest request);

    List<RouteResponse> getAllRoutes();

    RouteResponse getRouteById(String routeId);

    void deleteRoute(String routeId);
}
