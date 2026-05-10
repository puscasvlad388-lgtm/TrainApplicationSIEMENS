package com.ticketing.controller;

import com.ticketing.dto.TrainScheduleDto;
import com.ticketing.model.TrainSchedule;
import com.ticketing.service.TrainScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class TrainScheduleController {

    private final TrainScheduleService trainScheduleService;

    public TrainScheduleController(TrainScheduleService trainScheduleService) {
        this.trainScheduleService = trainScheduleService;
    }

    @GetMapping
    public List<TrainSchedule> getAllSchedules() {
        return trainScheduleService.getAllSchedules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainSchedule> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(trainScheduleService.getScheduleById(id));
    }

    @PostMapping
    public TrainSchedule createSchedule(@Valid @RequestBody TrainScheduleDto dto) {
        return trainScheduleService.createSchedule(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        trainScheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}