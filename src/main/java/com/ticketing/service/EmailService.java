package com.ticketing.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendBookingConfirmation(String to, String trainName, int seats) {
        System.out.println("Email Sent");
        System.out.println("To: " + to);
        System.out.println("Subject: Booking Confirmation - " + trainName);
        System.out.println("Message: Congratulations! You have successfully booked " + seats + " seat(s).");
        System.out.println("");
    }

    public void sendDelayNotification(String to, String trainName) {
        System.out.println("!EMAIL ALERT: Train " + trainName + " is delayed!");
    }
}