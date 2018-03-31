package by.samsolutions.internship.java.mygoals.mvc;

import by.samsolutions.internship.java.mygoals.dto.UpdateStageStateDto;
import by.samsolutions.internship.java.mygoals.service.StageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/stage")
public class StageController {

    private static final Logger logger = LoggerFactory.getLogger(StageController.class);

    @Autowired
    private StageService stageService;

    /**
     * Process POST request to '/stage/{id}' and
     * update stage state.
     *
     * @param stageDto stage dto
     * */
    @PostMapping
    @RequestMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStageState(@Valid @RequestBody UpdateStageStateDto stageDto) {
        stageService.update(stageDto);
    }

}