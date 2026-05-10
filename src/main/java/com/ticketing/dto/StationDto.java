package com.ticketing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StationDto {

    @NotBlank(message = "Station name cannot be blank")
    @Size(min = 2, max = 100, message = "Station name must be between 2 and 100 characters")
    private String name;

    public StationDto() {
    }

    public StationDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}