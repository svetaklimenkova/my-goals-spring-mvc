package by.samsolutions.internship.java.mygoals.dto;

import by.samsolutions.internship.java.mygoals.domain.State;

public class UpdateTaskStateDto {
    private int id;
    private State state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
