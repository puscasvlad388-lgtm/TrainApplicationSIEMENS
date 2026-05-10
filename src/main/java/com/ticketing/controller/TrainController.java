package com.ticketing.controller;

import com.ticketing.model.Train;
import com.ticketing.service.TrainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
        return ResponseEntity.ok(trainService.getTrainById(id));
    }

    @PostMapping
    public Train createTrain(@RequestBody Train train) {
        return trainService.createTrain(train);
    }

    @PatchMapping("/{id}/delay")
    public ResponseEntity<Train> updateDelay(@PathVariable Long id, @RequestParam boolean isDelayed) {
        return ResponseEntity.ok(trainService.updateTrainDelay(id, isDelayed));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable Long id) {
        trainService.deleteTrain(id);
        return ResponseEntity.noContent().build();
    }
}