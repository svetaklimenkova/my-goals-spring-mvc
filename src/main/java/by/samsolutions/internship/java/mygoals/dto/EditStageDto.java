package by.samsolutions.internship.java.mygoals.dto;

import java.util.List;

public class EditStageDto {
    private int id;
    private String name;
    private int order;
    private List<EditTaskDto> tasks;

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

    public List<EditTaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<EditTaskDto> tasks) {
        this.tasks = tasks;
    }
}
