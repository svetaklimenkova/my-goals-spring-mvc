package by.samsolutions.internship.java.mygoals.service.impl;

import by.samsolutions.internship.java.mygoals.dao.hibernate.StageDao;
import by.samsolutions.internship.java.mygoals.domain.Stage;
import by.samsolutions.internship.java.mygoals.dto.UpdateStageStateDto;
import by.samsolutions.internship.java.mygoals.service.StageService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StageServiceImpl implements StageService {

    private static Logger logger = LoggerFactory.getLogger(StageServiceImpl.class);

    @Autowired
    private StageDao stageDao;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Search stage by id
     *
     * @param idStage stage id
     *
     * @return stage
     * */
    @Override
    public Stage findById(int idStage) {
        return stageDao.findById(idStage);
    }

    /**
     * Search stages by goal id
     *
     * @param idGoal goal id
     *
     * @return stage list
     * */
    @Override
    public List<Stage> findAllByOwnerID(int idGoal) {
        return stageDao.findAllByOwnerID(idGoal);
    }

    /**
     * Insert stage in db
     *
     * @param stage stage
     *
     * @return stage id
     * */
    @Override
    public int create(Stage stage) {
        if (stage == null) {
            logger.error("Stage object is null.");
            return -1;
        }

        stageDao.insert(stage);
        return stage.getId();
    }

    /**
     * Update stage
     *
     * @param stage stage
     *
     * @return result of updating
     * */
    @Override
    public boolean update(Stage stage) {
        if (stage == null) {
            logger.error("Stage object is null.");
            return false;
        }

        stageDao.update(stage);
        return true;
    }

    /**
     * Delete stage by id
     *
     * @param idStage stage id
     *
     * @return result of deleting
     * */
    @Override
    public boolean remove(int idStage) {
        if (stageDao.findById(idStage) == null) {
            logger.error("Stage object with id = " + idStage + " doesn't exist.");
            return false;
        }

        stageDao.delete(idStage);
        return true;
    }

    /**
     * Update stage by stage dto
     *
     * @param stageDto stage dto
     *
     * @return result of updating
     * */
    @Override
    public boolean update(UpdateStageStateDto stageDto) {
        if (stageDto == null) {
            logger.error("Goal object is null.");
            return false;
        }
        Stage stage = findById(stageDto.getId());
        modelMapper.map(stageDto, stage);
        stageDao.update(stage);

        return true;
    }
}
