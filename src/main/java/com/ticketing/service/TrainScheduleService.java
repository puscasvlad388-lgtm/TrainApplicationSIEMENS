package com.ticketing.service;

import com.ticketing.dto.TrainScheduleDto;
import com.ticketing.model.Route;
import com.ticketing.model.Train;
import com.ticketing.model.TrainSchedule;
import com.ticketing.repository.RouteRepository;
import com.ticketing.repository.TrainRepository;
import com.ticketing.repository.TrainScheduleRepository;
import com.ticketing.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainScheduleService {

    private final TrainScheduleRepository trainScheduleRepository;
    private final TrainRepository trainRepository;
    private final RouteRepository routeRepository;

    public TrainScheduleService(TrainScheduleRepository trainScheduleRepository,
                                TrainRepository trainRepository,
                                RouteRepository routeRepository) {
        this.trainScheduleRepository = trainScheduleRepository;
        this.trainRepository = trainRepository;
        this.routeRepository = routeRepository;
    }

    public List<TrainSchedule> getAllSchedules() {
        return trainScheduleRepository.findAll();
    }

    public TrainSchedule getScheduleById(Long id) {
        return trainScheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id: " + id));
    }

    public TrainSchedule createSchedule(TrainScheduleDto dto) {
        Train train = trainRepository.findById(dto.getTrainId())
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + dto.getTrainId()));

        Route route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with id: " + dto.getRouteId()));

        TrainSchedule schedule = new TrainSchedule(train, route, dto.getDepartureTime());
        return trainScheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        TrainSchedule schedule = getScheduleById(id);
        trainScheduleRepository.delete(schedule);
    }
}