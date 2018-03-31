package by.samsolutions.internship.java.mygoals.dao.jdbc;

import by.samsolutions.internship.java.mygoals.domain.Goal;
import by.samsolutions.internship.java.mygoals.domain.State;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoalMapper implements RowMapper<Goal>{

    @Override
    public Goal mapRow(ResultSet rs, int i) throws SQLException {
        Goal goal = new Goal();

        goal.setId(rs.getInt("idgoal"));
        goal.setName(rs.getString("name"));
        goal.setDescription(rs.getString("description"));
        goal.setCreationDate(rs.getDate("creation_date"));
        goal.setStartDate(rs.getDate("start_date"));
        goal.setEndDate(rs.getDate("end_date"));
        goal.setState(State.get(rs.getInt("state")));
        return goal;
    }
}
