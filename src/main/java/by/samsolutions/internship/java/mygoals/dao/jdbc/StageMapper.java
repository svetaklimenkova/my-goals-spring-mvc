package by.samsolutions.internship.java.mygoals.dao.jdbc;

import by.samsolutions.internship.java.mygoals.domain.Stage;
import by.samsolutions.internship.java.mygoals.domain.State;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StageMapper implements RowMapper<Stage>{

    @Override
    public Stage mapRow(ResultSet rs, int i) throws SQLException {
        Stage stage = new Stage();

        stage.setId(rs.getInt("idstage"));
        stage.setName(rs.getString("name"));
        stage.setOrder(rs.getInt("order"));
        stage.setState(State.get(rs.getInt("state")));
//        stage.setIdGoal(rs.getInt("goal"));
        return stage;
    }
}
