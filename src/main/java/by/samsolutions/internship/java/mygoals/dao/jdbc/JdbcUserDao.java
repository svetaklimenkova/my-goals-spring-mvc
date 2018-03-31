package by.samsolutions.internship.java.mygoals.dao.jdbc;

import by.samsolutions.internship.java.mygoals.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcUserDao implements UserDAO {

    private static final String SQL_DELETE_FROM_USER_BY_IDUSER = "DELETE FROM user WHERE iduser = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO user " +
            "(login, password, mail, birthdate, creation_date, country, city) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_FROM_USER_BY_IDUSER = "SELECT * FROM user WHERE iduser = ?";
    private static final String SQL_SELECT_FROM_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String SQL_SELECT_FROM_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user " +
            "WHERE login = ? AND password = ?";
    private static final String SQL_SELECT_ALL_FROM_USER = "SELECT * FROM user";
    private static final String SQL_UPDATE_USER_BY_IDUSER = "UPDATE user " +
            "SET login = ?, password = ?, mail = ?, birthdate = ?, country = ?, city = ? WHERE iduser = ?";

    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(User user) {
        jdbcTemplateObject.update(SQL_INSERT_USER,
                user.getLogin(),
                user.getPassword(),
                user.getMail(),
                user.getBirthdayDate(),
                user.getCreationDate(),
                user.getCountry(),
                user.getCity());
    }

    @Override
    public User findById(int id) {
        return jdbcTemplateObject.queryForObject(SQL_SELECT_FROM_USER_BY_IDUSER, new Object[]{id}, new UserMapper());
    }

    @Override
    public User findByLogin(String login) {
        return jdbcTemplateObject.queryForObject(SQL_SELECT_FROM_USER_BY_LOGIN, new Object[]{login}, new UserMapper());
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return jdbcTemplateObject.queryForObject(SQL_SELECT_FROM_USER_BY_LOGIN_AND_PASSWORD,
                new Object[]{login, password}, new UserMapper());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplateObject.query(SQL_SELECT_ALL_FROM_USER, new UserMapper());
    }

    @Override
    public void update(User user) {
        jdbcTemplateObject.update(SQL_UPDATE_USER_BY_IDUSER,
                user.getLogin(),
                user.getPassword(),
                user.getMail(),
                user.getBirthdayDate(),
                user.getCountry(),
                user.getCity(),
                user.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplateObject.update(SQL_DELETE_FROM_USER_BY_IDUSER, id);
    }
}