package by.samsolutions.internship.java.mygoals.dto;

import by.samsolutions.internship.java.mygoals.domain.State;

import java.util.List;

public class ShowStageDto {
    private int id;
    private String name;
    private int order;
    private State state;
    private List<ShowTaskDto> tasks;

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<ShowTaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<ShowTaskDto> tasks) {
        this.tasks = tasks;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
