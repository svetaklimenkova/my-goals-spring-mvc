package by.samsolutions.internship.java.mygoals.dao.jdbc;

import by.samsolutions.internship.java.mygoals.domain.User;

import java.util.List;

public interface UserDAO {
    void insert(User e);
    User findById(int id);
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    List<User> findAll();
    void update(User user);
    void delete(int id);
}
