package by.samsolutions.internship.java.mygoals.mvc;

import by.samsolutions.internship.java.mygoals.domain.Goal;
import by.samsolutions.internship.java.mygoals.domain.User;
import by.samsolutions.internship.java.mygoals.dto.*;
import by.samsolutions.internship.java.mygoals.service.GoalService;
import by.samsolutions.internship.java.mygoals.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/goal")
public class GoalController {
    private static final Logger logger = LoggerFactory.getLogger(GoalController.class);

    @Autowired
    private GoalService goalService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Process GET request to '/goal' page and
     * return name of view with user goals
     *
     * @return view name
     * */
    @RequestMapping(method = RequestMethod.GET)
    public String getGoalListView() {
        return "goal/list";
    }

    /**
     * Process GET request to '/goal/create' page and
     * return name of view to create a goal
     *
     * @return view name
     * */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String getCreateGoalView() {
        return "goal/create";
    }

    /**
     * Process GET request to '/goal/edit' page and
     * return name of view to edit the goal.
     * If the goal does not belong to the user,
     * it return access error page name
     *
     * @param id goal id
     *
     * @return view name
     * */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String getEditGoalView(@PathVariable int id) {
        User user = getCurrentUser();

        Goal goal = goalService.findById(id);
        if (goal != null && user != null && goal.getUser().getId() == user.getId()) {
            return "goal/edit";
        } else {
            return "access_error";
        }
    }

    /**
     * Process GET request to '/goal/{id}' page and
     * return name of view with user goal by id.
     * If the goal does not belong to the user,
     * it return access error page name.
     *
     * @param id goal id
     *
     * @return view name
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getGoal(@PathVariable int id) {
        User user = getCurrentUser();

        Goal goal = goalService.findById(id);
        if (goal != null && user != null && goal.getUser().getId() == user.getId()) {
            return "goal/show";
        } else {
            return "access_error";
        }
    }

    /**
     * Process POST request to '/goal/list' and
     * return list of goals by authenticated user.
     *
     * @return list of goals
     * */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<?> getGoals() {
        User user = getCurrentUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Goal> goals = user.getGoals();
        List<ListViewGoalDto> goalDtos = goals.stream()
                .map(goal -> modelMapper.map(goal, ListViewGoalDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(goalDtos);
    }

    /**
     * Process GET request to '/goal/{id}/get' and
     * return goal dto to show by id
     *
     * @param id goal id
     *
     * @return show goal dto
     * */
    @ResponseBody
    @RequestMapping(value = {"{id}/get"}, method = RequestMethod.GET)
    public ResponseEntity<?> getGoalByIdToShow(@PathVariable("id") int id) {
        ShowGoalDto showGoalDto = goalService.findByIdToShow(id);
        return ResponseEntity.ok(showGoalDto);
    }

    /**
     * Process GET request to '/goal/{id}/edit/get' and
     * return goal dto to edit by id
     *
     * @param id goal id
     *
     * @return edit goal dto
     * */
    @ResponseBody
    @RequestMapping(value = {"{id}/edit/get"}, method = RequestMethod.GET)
    public ResponseEntity<?> getGoalByIdToEdit(@PathVariable("id") int id) {
        EditGoalDto editGoalDto = goalService.findByIdToEdit(id);
        return ResponseEntity.ok(editGoalDto);
    }

    /**
     * Process POST request to '/goal' and
     * add goal to db
     *
     * @param goalDto dto to add goal
     * @param result result of validation
     *
     * @return id of added goal
     * */
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addGoal(@Valid @RequestBody AddGoalDto goalDto, BindingResult result) throws BindException {
        if(result.hasErrors()) {
            throw new BindException(result);
        }

        Goal goal = modelMapper.map(goalDto, Goal.class);
        goal.setUser(getCurrentUser());

        int id = goalService.create(goal);

        if (id == -1) {
            ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(id);
    }

    /**
     * Process POST request to '/goal/{id}/state' and
     * update goal state
     *
     * @param goalDto updated goal dto
     * */
    @RequestMapping(value="/{id}/state", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGoalState(@RequestBody UpdateGoalStateDto goalDto) {
        goalService.update(goalDto);
    }

    /**
     * Process POST request to '/goal/{id}' and
     * update the goal
     *
     * @param goalDto updated goal dto
     * */
    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putGoal(@Valid @RequestBody EditGoalDto goalDto) {
        goalService.update(goalDto);
    }

    /**
     * Process DELETE request to '/goal/{id}' and
     * delete the goal by id from db
     *
     * @param id goal id
     * */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGoal(@PathVariable("id") int id) {
        goalService.remove(id);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (null == auth) {
            return null;
        }

        Object obj = auth.getPrincipal();
        String username;

        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }

        return userService.findUserByLogin(username);
    }
}