package com.ticketing.controller;

import com.ticketing.dto.BookingDto;
import com.ticketing.service.BookingService;
import com.ticketing.service.SearchService;
import com.ticketing.service.StationService;
import com.ticketing.service.TrainScheduleService;
import com.ticketing.service.TrainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    private final TrainScheduleService scheduleService;
    private final StationService stationService;
    private final BookingService bookingService;
    private final TrainService trainService;
    private final SearchService searchService;

    public ViewController(TrainScheduleService scheduleService, StationService stationService,
                          BookingService bookingService, TrainService trainService, SearchService searchService) {
        this.scheduleService = scheduleService;
        this.stationService = stationService;
        this.bookingService = bookingService;
        this.trainService = trainService;
        this.searchService = searchService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam Long fromId, @RequestParam Long toId, Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        model.addAttribute("paths", searchService.findPaths(fromId, toId));
        model.addAttribute("fromId", fromId);
        model.addAttribute("toId", toId);
        return "index";
    }

    @PostMapping("/book")
    public String processBooking(@RequestParam Long scheduleId, @RequestParam String email,
                                 @RequestParam int seats, @RequestParam Long startStationId,
                                 @RequestParam Long endStationId) {
        try {
            BookingDto dto = new BookingDto();
            dto.setScheduleId(scheduleId);
            dto.setCustomerEmail(email);
            dto.setNumberOfTickets(seats);
            dto.setStartStationId(startStationId);
            dto.setEndStationId(endStationId);
            bookingService.createBooking(dto);
            return "redirect:/?success=Booking successful";
        } catch (Exception e) {
            return "redirect:/?error=" + e.getMessage();
        }
    }
}