package by.samsolutions.internship.java.mygoals.dto;

import java.util.Date;
import java.util.List;

public class EditGoalDto {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<EditStageDto> stages;

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

    public List<EditStageDto> getStages() {
        return stages;
    }

    public void setStages(List<EditStageDto> stages) {
        this.stages = stages;
    }
}
