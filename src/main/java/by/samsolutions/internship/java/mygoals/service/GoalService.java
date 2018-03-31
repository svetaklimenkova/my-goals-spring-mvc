package by.samsolutions.internship.java.mygoals.service;

import by.samsolutions.internship.java.mygoals.domain.Goal;
import by.samsolutions.internship.java.mygoals.dto.EditGoalDto;
import by.samsolutions.internship.java.mygoals.dto.ShowGoalDto;
import by.samsolutions.internship.java.mygoals.dto.UpdateGoalStateDto;

public interface GoalService extends EntityService<Goal> {
    ShowGoalDto findByIdToShow(int id);
    EditGoalDto findByIdToEdit(int id);
    boolean update(EditGoalDto goalDto);
    boolean update(UpdateGoalStateDto goalDto);
}
