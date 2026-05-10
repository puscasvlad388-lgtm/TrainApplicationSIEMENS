package com.ticketing.controller;

import com.ticketing.dto.BookingDto;
import com.ticketing.model.Booking;
import com.ticketing.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/customer")
    public List<Booking> getBookingsByEmail(@RequestParam String email) {
        return bookingService.getBookingsByEmail(email);
    }

    @PostMapping
    public Booking createBooking(@Valid @RequestBody BookingDto bookingDto) {
        return bookingService.createBooking(bookingDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }
}