package by.samsolutions.internship.java.mygoals.mvc;

import by.samsolutions.internship.java.mygoals.dto.UpdateTaskStateDto;
import by.samsolutions.internship.java.mygoals.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    /**
     * Process POST request to '/task/{id}' and
     * update task state.
     *
     * @param taskDto task dto
     * */
    @PostMapping
    @RequestMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@Valid @RequestBody UpdateTaskStateDto taskDto) {
        taskService.update(taskDto);
    }

}