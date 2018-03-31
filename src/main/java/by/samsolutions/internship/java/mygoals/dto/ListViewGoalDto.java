package by.samsolutions.internship.java.mygoals.dto;

import by.samsolutions.internship.java.mygoals.domain.State;

public class ListViewGoalDto {
    private int id;
    private String name;
    private State state;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
