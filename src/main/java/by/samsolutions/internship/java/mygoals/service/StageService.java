package by.samsolutions.internship.java.mygoals.service;

import by.samsolutions.internship.java.mygoals.domain.Stage;
import by.samsolutions.internship.java.mygoals.dto.UpdateStageStateDto;

public interface StageService extends EntityService<Stage> {
    boolean update(UpdateStageStateDto stageDto);
}
