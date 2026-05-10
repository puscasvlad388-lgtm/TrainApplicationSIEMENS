package com.ticketing.service;

import com.ticketing.model.Station;
import com.ticketing.repository.StationRepository;
import com.ticketing.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStationById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Station not found with id: " + id));
    }

    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    public void deleteStation(Long id) {
        Station station = getStationById(id);
        stationRepository.delete(station);
    }
}