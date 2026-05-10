package com.ticketing.service;

import com.ticketing.dto.RouteDto;
import com.ticketing.model.Route;
import com.ticketing.model.Station;
import com.ticketing.repository.RouteRepository;
import com.ticketing.repository.StationRepository;
import com.ticketing.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final StationRepository stationRepository;

    public RouteService(RouteRepository routeRepository, StationRepository stationRepository) {
        this.routeRepository = routeRepository;
        this.stationRepository = stationRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with id: " + id));
    }

    public Route createRoute(RouteDto routeDto) {
        List<Station> stations = routeDto.getStationIds().stream()
                .map(id -> stationRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Station not found with id: " + id)))
                .collect(Collectors.toList());

        Route route = new Route(routeDto.getName(), stations);
        return routeRepository.save(route);
    }

    public void deleteRoute(Long id) {
        Route route = getRouteById(id);
        routeRepository.delete(route);
    }
}