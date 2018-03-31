package by.samsolutions.internship.java.mygoals.dao.jdbc;

import by.samsolutions.internship.java.mygoals.domain.State;
import by.samsolutions.internship.java.mygoals.domain.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task>{

    @Override
    public Task mapRow(ResultSet rs, int i) throws SQLException {
        Task task = new Task();

        task.setId(rs.getInt("idtask"));
        task.setName(rs.getString("name"));
        task.setState(State.get(rs.getInt("state")));
//        task.setIdStage(rs.getInt("stage"));

        return task;
    }
}
