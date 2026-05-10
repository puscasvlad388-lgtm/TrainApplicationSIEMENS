package com.ticketing.service;

import com.ticketing.dto.SearchPathDto;
import com.ticketing.model.Station;
import com.ticketing.model.TrainSchedule;
import com.ticketing.repository.TrainScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final TrainScheduleRepository scheduleRepository;

    public SearchService(TrainScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<SearchPathDto> findPaths(Long fromId, Long toId) {
        List<SearchPathDto> results = new ArrayList<>();
        List<TrainSchedule> allSchedules = scheduleRepository.findAll();

        for (TrainSchedule schedule : allSchedules) {
            if (hasStationsInOrder(schedule, fromId, toId)) {
                results.add(new SearchPathDto(List.of(schedule), true));
            }
        }

        if (results.isEmpty()) {
            for (TrainSchedule firstLeg : allSchedules) {
                if (containsStation(firstLeg, fromId) && !containsStation(firstLeg, toId)) {
                    for (Station transferStation : getStationsAfter(firstLeg, fromId)) {
                        for (TrainSchedule secondLeg : allSchedules) {
                            if (!firstLeg.getId().equals(secondLeg.getId()) &&
                                    hasStationsInOrder(secondLeg, transferStation.getId(), toId) &&
                                    secondLeg.getDepartureTime().isAfter(firstLeg.getDepartureTime())) {

                                results.add(new SearchPathDto(List.of(firstLeg, secondLeg), false));
                            }
                        }
                    }
                }
            }
        }

        return results;
    }

    private boolean hasStationsInOrder(TrainSchedule schedule, Long startId, Long endId) {
        List<Station> stations = schedule.getRoute().getStations();
        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getId().equals(startId)) {
                startIndex = i;
            }
            if (stations.get(i).getId().equals(endId)) {
                endIndex = i;
            }
        }

        return startIndex != -1 && endIndex != -1 && startIndex < endIndex;
    }

    private boolean containsStation(TrainSchedule schedule, Long stationId) {
        return schedule.getRoute().getStations().stream()
                .anyMatch(s -> s.getId().equals(stationId));
    }

    private List<Station> getStationsAfter(TrainSchedule schedule, Long stationId) {
        List<Station> stations = schedule.getRoute().getStations();
        List<Station> after = new ArrayList<>();
        boolean found = false;

        for (Station s : stations) {
            if (found) {
                after.add(s);
            }
            if (s.getId().equals(stationId)) {
                found = true;
            }
        }

        return after;
    }
}