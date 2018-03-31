package by.samsolutions.internship.java.mygoals.dao.jdbc;

import by.samsolutions.internship.java.mygoals.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("iduser"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setMail(rs.getString("mail"));
        user.setBirthdayDate(rs.getDate("birthdate"));
        user.setCreationDate(rs.getDate("creation_date"));
        user.setCountry(rs.getString("country"));
        user.setCity(rs.getString("city"));

        return user;
    }
}