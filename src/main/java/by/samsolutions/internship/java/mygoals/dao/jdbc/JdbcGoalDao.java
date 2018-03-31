package by.samsolutions.internship.java.mygoals.dao.jdbc;

import by.samsolutions.internship.java.mygoals.domain.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcGoalDao implements DAO<Goal> {
    private static final String SQL_INSERT_GOAL = "INSERT INTO goal " +
            "(name, description, creation_date, start_date, end_date, state, owner) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_FROM_GOAL_BY_IDGOAL = "SELECT * FROM goal WHERE idgoal = ?";
    private static final String SQL_SELECT_FROM_GOAL_BY_OWNER = "SELECT * FROM goal WHERE owner = ?";
    private static final String SQL_SELECT_ALL_FROM_GOAL = "SELECT * FROM goal";
    private static final String SQL_UPDATE_GOAL_BY_IDGOAL = "UPDATE goal SET " +
            "name = ?, description = ?, start_date = ?, end_date = ?, state = ? WHERE idgoal = ?";
    private static final String SQL_DELETE_FROM_GOAL_BY_IDGOAL = "DELETE FROM goal WHERE idgoal = ?";
    private static final String SQL_SELECT_LAST_GOAL = "SELECT * FROM goal WHERE owner = ? ORDER BY idgoal DESC LIMIT 1";

    private JdbcTemplate jdbcTemplateObject;

    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(Goal goal) {
        jdbcTemplateObject.update(SQL_INSERT_GOAL,
                goal.getName(),
                goal.getDescription(),
                goal.getCreationDate(),
                goal.getStartDate(),
                goal.getEndDate(),
                goal.getState().getI());
    }

    @Override
    public Goal findById(int id) {
        return jdbcTemplateObject.queryForObject(SQL_SELECT_FROM_GOAL_BY_IDGOAL, new Object[]{id}, new GoalMapper());
    }

    public Goal findLastByOwnerId(int idOwner) {
        return jdbcTemplateObject.queryForObject(SQL_SELECT_LAST_GOAL, new Object[]{idOwner}, new GoalMapper());
    }

    @Override
    public List<Goal> findAllByOwnerID(int idOwner) {
        return jdbcTemplateObject.query(SQL_SELECT_FROM_GOAL_BY_OWNER, new Object[]{idOwner}, new GoalMapper());
    }

    @Override
    public List<Goal> findAll() {
        return jdbcTemplateObject.query(SQL_SELECT_ALL_FROM_GOAL, new GoalMapper());
    }

    @Override
    public void update(Goal goal) {
        jdbcTemplateObject.update(SQL_UPDATE_GOAL_BY_IDGOAL,
                goal.getName(),
                goal.getDescription(),
                goal.getStartDate(),
                goal.getEndDate(),
                goal.getState().getI(),
                goal.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplateObject.update(SQL_DELETE_FROM_GOAL_BY_IDGOAL, id);
    }
}