package com.ticketing.controller;

import com.ticketing.dto.RouteDto;
import com.ticketing.dto.TrainScheduleDto;
import com.ticketing.model.Station;
import com.ticketing.model.Train;
import com.ticketing.service.BookingService;
import com.ticketing.service.RouteService;
import com.ticketing.service.StationService;
import com.ticketing.service.TrainScheduleService;
import com.ticketing.service.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TrainService trainService;
    private final RouteService routeService;
    private final StationService stationService;
    private final BookingService bookingService;
    private final TrainScheduleService scheduleService;

    public AdminController(TrainService trainService, RouteService routeService,
                           StationService stationService, BookingService bookingService,
                           TrainScheduleService scheduleService) {
        this.trainService = trainService;
        this.routeService = routeService;
        this.stationService = stationService;
        this.bookingService = bookingService;
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("trains", trainService.getAllTrains());
        model.addAttribute("routes", routeService.getAllRoutes());
        model.addAttribute("stations", stationService.getAllStations());
        model.addAttribute("bookings", bookingService.getAllBookings());
        model.addAttribute("schedules", scheduleService.getAllSchedules());
        return "admin";
    }

    @PostMapping("/trains/add")
    public String addTrain(@RequestParam String name, @RequestParam Integer capacity) {
        Train train = new Train(name, capacity);
        trainService.createTrain(train);
        return "redirect:/admin";
    }

    @PostMapping("/trains/delete")
    public String deleteTrain(@RequestParam Long id) {
        trainService.deleteTrain(id);
        return "redirect:/admin";
    }

    @PostMapping("/stations/add")
    public String addStation(@RequestParam String name) {
        Station station = new Station(name);
        stationService.createStation(station);
        return "redirect:/admin";
    }

    @PostMapping("/routes/add")
    public String addRoute(@RequestParam String name, @RequestParam List<Long> stationIds) {
        RouteDto dto = new RouteDto(name, stationIds);
        routeService.createRoute(dto);
        return "redirect:/admin";
    }

    @PostMapping("/delay")
    public String markDelay(@RequestParam Long trainId) {
        trainService.updateTrainDelay(trainId, true);
        return "redirect:/admin";
    }

    @PostMapping("/schedules/add")
    public String addSchedule(@RequestParam Long trainId,
                              @RequestParam Long routeId,
                              @RequestParam String departureTime) {
        TrainScheduleDto dto = new TrainScheduleDto();
        dto.setTrainId(trainId);
        dto.setRouteId(routeId);
        dto.setDepartureTime(LocalDateTime.parse(departureTime));
        scheduleService.createSchedule(dto);
        return "redirect:/admin";
    }
}