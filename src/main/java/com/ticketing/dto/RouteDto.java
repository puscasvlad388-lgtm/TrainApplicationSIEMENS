package com.ticketing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

public class RouteDto {

    @NotBlank(message = "Route name cannot be blank")
    @Size(min = 2, max = 100, message = "Route name must be between 2 and 100 characters")
    private String name;

    @NotEmpty(message = "Station list cannot be empty")
    @Size(min = 2, message = "A route must contain at least two stations")
    private List<Long> stationIds;

    public RouteDto() {
    }

    public RouteDto(String name, List<Long> stationIds) {
        this.name = name;
        this.stationIds = stationIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getStationIds() {
        return stationIds;
    }

    public void setStationIds(List<Long> stationIds) {
        this.stationIds = stationIds;
    }
}