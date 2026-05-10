package com.ticketing.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookingDto {

    @NotBlank(message = "Customer email cannot be blank")
    @Email(message = "Invalid email format")
    private String customerEmail;

    @NotNull(message = "Schedule ID is required")
    private Long scheduleId;

    @NotNull(message = "Start station ID is required")
    private Long startStationId;

    @NotNull(message = "End station ID is required")
    private Long endStationId;

    @NotNull(message = "Number of tickets is required")
    @Min(value = 1, message = "You must book at least 1 ticket")
    private Integer numberOfTickets;

    public BookingDto() {
    }

    public BookingDto(String customerEmail, Long scheduleId, Long startStationId, Long endStationId, Integer numberOfTickets) {
        this.customerEmail = customerEmail;
        this.scheduleId = scheduleId;
        this.startStationId = startStationId;
        this.endStationId = endStationId;
        this.numberOfTickets = numberOfTickets;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(Long startStationId) {
        this.startStationId = startStationId;
    }

    public Long getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(Long endStationId) {
        this.endStationId = endStationId;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}