package by.samsolutions.internship.java.mygoals.dao.jdbc;

import by.samsolutions.internship.java.mygoals.domain.Stage;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcStageDao implements DAO<Stage> {

    private static final String SQL_INSERT_STAGE = "INSERT INTO stage " +
            "(name, `order`, state, goal) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_FROM_STAGE_BY_IDSTAGE = "SELECT * FROM stage WHERE idstage = ?";
    private static final String SQL_SELECT_FROM_STAGE_BY_GOAL = "SELECT * FROM stage WHERE goal = ?";
    private static final String SQL_SELECT_FROM_STAGE_BY_GOAL_AND_ORDER = "SELECT * FROM stage WHERE goal = ? AND stage.order = ?";
    private static final String SQL_SELECT_ALL_FROM_STAGE = "SELECT * FROM stage";
    private static final String SQL_UPDATE_STAGE_BY_IDSTAGE = "UPDATE stage SET " +
            "name = ?, `order` = ?, goal = ?, state = ?, goal = ? WHERE idstage = ?";
    private static final String SQL_DELETE_FROM_STAGE_BY_IDSTAGE = "DELETE FROM stage WHERE idstage = ?";


    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(Stage stage) {
        jdbcTemplateObject.update(SQL_INSERT_STAGE,
                stage.getName(),
                stage.getOrder(),
                stage.getState().getI());
//                stage.getIdGoal());
    }

    @Override
    public Stage findById(int id) {
        return jdbcTemplateObject.queryForObject(SQL_SELECT_FROM_STAGE_BY_IDSTAGE, new Object[]{id}, new StageMapper());
    }

    public Stage findByOwnerIdAndOrder(int idOwner, int order) {
        return jdbcTemplateObject.queryForObject(SQL_SELECT_FROM_STAGE_BY_GOAL_AND_ORDER, new Object[]{idOwner, order}, new StageMapper());
    }

    @Override
    public List<Stage> findAllByOwnerID(int idOwner) {
        return jdbcTemplateObject.query(SQL_SELECT_FROM_STAGE_BY_GOAL, new Object[]{idOwner}, new StageMapper());
    }

    @Override
    public List<Stage> findAll() {
        return jdbcTemplateObject.query(SQL_SELECT_ALL_FROM_STAGE, new StageMapper());
    }

    @Override
    public void update(Stage stage) {
        jdbcTemplateObject.update(SQL_UPDATE_STAGE_BY_IDSTAGE,
                stage.getName(),
                stage.getOrder(),
//                stage.getIdGoal(),
                stage.getState().getI(),
//                stage.getIdGoal(),
                stage.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplateObject.update(SQL_DELETE_FROM_STAGE_BY_IDSTAGE, id);
    }

    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }
}