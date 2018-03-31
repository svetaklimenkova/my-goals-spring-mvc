package by.samsolutions.internship.java.mygoals.service;

import by.samsolutions.internship.java.mygoals.domain.Task;
import by.samsolutions.internship.java.mygoals.dto.UpdateTaskStateDto;

public interface TaskService extends EntityService<Task> {
    boolean update(UpdateTaskStateDto taskDto);
}
