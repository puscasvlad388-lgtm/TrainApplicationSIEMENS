package com.ticketing.service;

import com.ticketing.model.Train;
import com.ticketing.model.Booking;
import com.ticketing.repository.TrainRepository;
import com.ticketing.repository.BookingRepository;
import com.ticketing.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainService {

    private final TrainRepository trainRepository;
    private final BookingRepository bookingRepository;
    private final EmailService emailService;

    public TrainService(TrainRepository trainRepository,
                        BookingRepository bookingRepository,
                        EmailService emailService) {
        this.trainRepository = trainRepository;
        this.bookingRepository = bookingRepository;
        this.emailService = emailService;
    }

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public Train getTrainById(Long id) {
        return trainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + id));
    }

    public Train createTrain(Train train) {
        return trainRepository.save(train);
    }

    @Transactional
    public Train updateTrainDelay(Long id, boolean isDelayed) {
        Train train = getTrainById(id);
        train.setIsDelayed(isDelayed);
        Train savedTrain = trainRepository.save(train);

        if (isDelayed) {
            List<Booking> affectedBookings = bookingRepository.findByScheduleTrainId(id);
            for (Booking booking : affectedBookings) {
                emailService.sendDelayNotification(booking.getCustomerEmail(), train.getName());
            }
        }

        return savedTrain;
    }

    public void deleteTrain(Long id) {
        Train train = getTrainById(id);
        trainRepository.delete(train);
    }
}