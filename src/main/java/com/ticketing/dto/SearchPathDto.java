package com.ticketing.dto;

import com.ticketing.model.TrainSchedule;
import java.util.List;

public class SearchPathDto {

    private List<TrainSchedule> legs;
    private boolean direct;

    public SearchPathDto() {
    }

    public SearchPathDto(List<TrainSchedule> legs, boolean direct) {
        this.legs = legs;
        this.direct = direct;
    }

    public List<TrainSchedule> getLegs() {
        return legs;
    }

    public void setLegs(List<TrainSchedule> legs) {
        this.legs = legs;
    }

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }
}