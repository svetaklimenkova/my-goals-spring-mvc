package by.samsolutions.internship.java.mygoals.service.impl;

import by.samsolutions.internship.java.mygoals.dao.hibernate.TaskDao;
import by.samsolutions.internship.java.mygoals.domain.Task;
import by.samsolutions.internship.java.mygoals.dto.UpdateTaskStateDto;
import by.samsolutions.internship.java.mygoals.service.TaskService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Search task by id
     *
     * @param idTask task id
     *
     * @return task
     * */
    @Override
    public Task findById(int idTask) {
        return taskDao.findById(idTask);
    }

    /**
     * Search tasks by stage id
     *
     * @param idStage stage id
     *
     * @return task list
     * */
    @Override
    public List<Task> findAllByOwnerID(int idStage) {
        return taskDao.findAllByOwnerID(idStage);
    }

    /**
     * Create task
     *
     * @param task task
     *
     * @return task id
     * */
    @Override
    public int create(Task task) {
        if (task == null) {
            logger.error("Task object is null.");
            return -1;
        }

        taskDao.insert(task);
        return task.getId();
    }

    /**
     * Update task
     *
     * @param task stage
     *
     * @return result of updating
     * */
    @Override
    public boolean update(Task task) {
        if (task == null) {
            logger.error("Task object is null.");
            return false;
        }

        taskDao.update(task);
        return true;
    }

    /**
     * Delete task
     *
     * @param idTask task id
     *
     * @return result of deleting
     * */
    @Override
    public boolean remove(int idTask) {
        if (taskDao.findById(idTask) == null) {
            logger.error("Task object with id = " + idTask + "doesn't exist");
            return false;
        }

        taskDao.delete(idTask);
        return false;
    }

    /**
     * Update task by task dto
     *
     * @param taskDto task dto
     *
     * @return result of updating
     * */
    @Override
    public boolean update(UpdateTaskStateDto taskDto) {
        Task task = findById(taskDto.getId());
        modelMapper.map(taskDto, task);
        taskDao.update(task);
        return true;
    }
}