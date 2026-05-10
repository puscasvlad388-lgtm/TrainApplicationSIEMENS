package com.ticketing.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerEmail;

    @ManyToOne(optional = false)
    @JoinColumn(name = "schedule_id")
    private TrainSchedule schedule;

    @ManyToOne(optional = false)
    @JoinColumn(name = "start_station_id")
    private Station startStation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "end_station_id")
    private Station endStation;

    @Column(nullable = false)
    private int numberOfTickets;

    public Booking() {
    }

    public Booking(String customerEmail, TrainSchedule schedule, Station startStation, Station endStation, int numberOfTickets) {
        this.customerEmail = customerEmail;
        this.schedule = schedule;
        this.startStation = startStation;
        this.endStation = endStation;
        this.numberOfTickets = numberOfTickets;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public TrainSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(TrainSchedule schedule) {
        this.schedule = schedule;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}