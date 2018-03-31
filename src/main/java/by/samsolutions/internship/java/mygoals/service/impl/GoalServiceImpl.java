package by.samsolutions.internship.java.mygoals.service.impl;

import by.samsolutions.internship.java.mygoals.dao.hibernate.GoalDao;
import by.samsolutions.internship.java.mygoals.dao.hibernate.StageDao;
import by.samsolutions.internship.java.mygoals.dao.hibernate.TaskDao;
import by.samsolutions.internship.java.mygoals.domain.Goal;
import by.samsolutions.internship.java.mygoals.domain.Stage;
import by.samsolutions.internship.java.mygoals.domain.Task;
import by.samsolutions.internship.java.mygoals.dto.EditGoalDto;
import by.samsolutions.internship.java.mygoals.dto.ShowGoalDto;
import by.samsolutions.internship.java.mygoals.dto.UpdateGoalStateDto;
import by.samsolutions.internship.java.mygoals.service.GoalService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("goalService")
public class GoalServiceImpl implements GoalService {

    private static Logger logger = LoggerFactory.getLogger(GoalServiceImpl.class);

    @Autowired
    private GoalDao goalDao;

    @Autowired
    private StageDao stageDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Search goal by id. If goal doesn't exist,
     * it return null.
     *
     * @param idGoal goal id
     *
     * @return goal
     * */
    @Override
    public Goal findById(int idGoal) {
        Goal goal = goalDao.findById(idGoal);
        if (goal != null) {
            Hibernate.initialize(goal.getUser());
        }
        return goal;
    }

    /**
     * Search show goal dto by goal id. If goal doesn't exist,
     * it return null.
     *
     * @param id goal id
     *
     * @return show goal dto
     * */
    @Override
    public ShowGoalDto findByIdToShow(int id) {
        Goal goal = goalDao.findById(id);
        if (goal == null) {
            return null;
        }
        return modelMapper.map(goal, ShowGoalDto.class);
    }

    /**
     * Search edit goal dto by goal id. If goal doesn't exist,
     * it return null.
     *
     * @param id goal id
     *
     * @return edit goal dto
     * */
    @Override
    public EditGoalDto findByIdToEdit(int id) {
        Goal goal = goalDao.findById(id);
        return modelMapper.map(goal, EditGoalDto.class);
    }

    /**
     * Search all goal by user id.
     *
     * @param idUser user id
     *
     * @return list of goal
     * */
    @Override
    public List<Goal> findAllByOwnerID(int idUser) {
        return goalDao.findAllByOwnerID(idUser);
    }

    /**
     * Insert goal in db.
     *
     * @param goal goal
     *
     * @return goal id
     * */
    @Override
    public int create(Goal goal) {
        if (goal == null) {
            logger.error("Goal object is null.");
            return -1;
        }

        goalDao.insert(goal);

        return goal.getId();
    }

    /**
     * Update goal by goal dto.
     *
     * @param goalDto edit goal dto
     *
     * @return result of updating
     * */
    @Override
    public boolean update(EditGoalDto goalDto) {
        if (goalDto == null) {
            logger.error("Goal object is null.");
            return false;
        }
        Goal goal = findById(goalDto.getId());
        modelMapper.map(goalDto, goal);

        addStageState(goal.getStages());

        goalDao.update(goal);
        return true;
    }

    /**
     * Update goal state by goal dto.
     *
     * @param goalDto edit goal state dto
     *
     * @return result of updating
     * */
    @Override
    public boolean update(UpdateGoalStateDto goalDto) {
        if (goalDto == null) {
            logger.error("Goal object is null.");
            return false;
        }
        Goal goal = findById(goalDto.getId());
        modelMapper.map(goalDto, goal);

        goalDao.update(goal);
        return true;
    }

    /**
     * Update goal.
     *
     * @param goal goal
     *
     * @return result of updating
     * */
    @Override
    public boolean update(Goal goal) {
        if (goal == null) {
            logger.error("Goal object is null.");
            return false;
        }

        goalDao.update(goal);

        return true;
    }

    /**
     * Delete goal by goal id.
     *
     * @param idGoal goal id
     *
     * @return result of deleting
     * */
    @Override
    public boolean remove(int idGoal) {
        if (goalDao.findById(idGoal) == null) {
            logger.error("Goal object with id = " + idGoal + " doesn't exist");
            return false;
        }

        goalDao.delete(idGoal);
        return true;
    }

    private void addStageState(List<Stage> stages) {
        if (stages == null) {
            return;
        }

        for (Stage stage : stages) {
            if (stageDao.findById(stage.getId()) == null) {
                continue;
            }

            stage.setState(stageDao.findById(stage.getId()).getState());
            addTaskState(stage.getTasks());
        }
    }

    private void addTaskState(List<Task> tasks) {
        if (tasks == null) {
            return;
        }

        for (Task task : tasks) {
            if (taskDao.findById(task.getId()) == null) {
                continue;
            }
            task.setState(taskDao.findById(task.getId()).getState());
        }
    }
}
