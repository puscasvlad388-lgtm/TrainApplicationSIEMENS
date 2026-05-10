package com.ticketing.service;

import com.ticketing.dto.BookingDto;
import com.ticketing.exception.BookingException;
import com.ticketing.exception.ResourceNotFoundException;
import com.ticketing.model.Booking;
import com.ticketing.model.Station;
import com.ticketing.model.TrainSchedule;
import com.ticketing.repository.BookingRepository;
import com.ticketing.repository.StationRepository;
import com.ticketing.repository.TrainScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TrainScheduleRepository scheduleRepository;
    private final StationRepository stationRepository;
    private final EmailService emailService;

    public BookingService(BookingRepository bookingRepository,
                          TrainScheduleRepository scheduleRepository,
                          StationRepository stationRepository,
                          EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.scheduleRepository = scheduleRepository;
        this.stationRepository = stationRepository;
        this.emailService = emailService;
    }

    @Transactional
    public Booking createBooking(BookingDto dto) {
        TrainSchedule schedule = scheduleRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new BookingException("Schedule not found"));

        if (schedule.getAvailableSeats() < dto.getNumberOfTickets()) {
            throw new BookingException("Not enough seats");
        }

        Station startStation = stationRepository.findById(dto.getStartStationId())
                .orElseThrow(() -> new BookingException("Start station not found"));

        Station endStation = stationRepository.findById(dto.getEndStationId())
                .orElseThrow(() -> new BookingException("End station not found"));

        schedule.setAvailableSeats(schedule.getAvailableSeats() - dto.getNumberOfTickets());
        scheduleRepository.save(schedule);

        Booking booking = new Booking();
        booking.setCustomerEmail(dto.getCustomerEmail());
        booking.setNumberOfTickets(dto.getNumberOfTickets());
        booking.setSchedule(schedule);
        booking.setStartStation(startStation);
        booking.setEndStation(endStation);

        Booking saved = bookingRepository.save(booking);
        emailService.sendBookingConfirmation(dto.getCustomerEmail(), schedule.getTrain().getName(), dto.getNumberOfTickets());

        return saved;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByEmail(String email) {
        return bookingRepository.findByCustomerEmail(email);
    }

    @Transactional
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        TrainSchedule schedule = booking.getSchedule();
        schedule.setAvailableSeats(schedule.getAvailableSeats() + booking.getNumberOfTickets());

        scheduleRepository.save(schedule);
        bookingRepository.delete(booking);
    }
}