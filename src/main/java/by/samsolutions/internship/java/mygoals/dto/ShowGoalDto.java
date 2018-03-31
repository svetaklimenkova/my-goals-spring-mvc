package by.samsolutions.internship.java.mygoals.dto;

import by.samsolutions.internship.java.mygoals.domain.State;

import java.util.Date;
import java.util.List;

public class ShowGoalDto {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private State state;
    private List<ShowStageDto> stages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate != null ? new Date(startDate.getTime()) : null;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate != null ? new Date(startDate.getTime()) : null;
    }

    public Date getEndDate() {
        return endDate != null ? new Date(endDate.getTime()) : null;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate != null ? new Date(endDate.getTime()) : null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setStages(List<ShowStageDto> stages) {
        this.stages = stages;
    }

    public List<ShowStageDto> getStages() {
        return stages;
    }
}
