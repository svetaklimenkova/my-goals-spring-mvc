package by.samsolutions.internship.java.mygoals.dao.jdbc;

import by.samsolutions.internship.java.mygoals.domain.Task;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTaskDao implements DAO<Task> {

    private static final String SQL_INSERT_TASK = "INSERT INTO task (name, state, stage) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_FROM_TASK_BY_IDTASK = "SELECT * FROM task WHERE idtask = ?";
    private static final String SQL_SELECT_FROM_TASK_BY_STAGE = "SELECT * FROM task WHERE stage = ?";
    private static final String SQL_SELECT_ALL_FROM_TASK = "SELECT * FROM task";
    private static final String SQL_UPDATE_TASK_BY_IDTASK = "UPDATE task SET name = ?, state = ?, stage = ? WHERE idtask = ?";
    private static final String SQL_DELETE_FROM_TASK_BY_IDTASK = "DELETE FROM task WHERE idtask = ?";

    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(Task stage) {
        jdbcTemplateObject.update(SQL_INSERT_TASK,
                stage.getName(),
                stage.getState().getI());
//                stage.getIdStage());
    }

    @Override
    public Task findById(int id) {
        return jdbcTemplateObject.queryForObject(SQL_SELECT_FROM_TASK_BY_IDTASK, new Object[]{id}, new TaskMapper());
    }

    @Override
    public List<Task> findAllByOwnerID(int idOwner) {
        return jdbcTemplateObject.query(SQL_SELECT_FROM_TASK_BY_STAGE, new Object[]{idOwner}, new TaskMapper());
    }

    @Override
    public List<Task> findAll() {
        return jdbcTemplateObject.query(SQL_SELECT_ALL_FROM_TASK, new TaskMapper());
    }

    @Override
    public void update(Task stage) {
        jdbcTemplateObject.update(SQL_UPDATE_TASK_BY_IDTASK,
                stage.getName(),
                stage.getState().getI(),
//                stage.getIdStage(),
                stage.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplateObject.update(SQL_DELETE_FROM_TASK_BY_IDTASK, id);
    }

    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }
}