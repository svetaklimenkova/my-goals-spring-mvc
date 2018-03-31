package by.samsolutions.internship.java.mygoals.dao.hibernate;

import by.samsolutions.internship.java.mygoals.domain.User;

public interface UserDAO {
    void insert(User e);
    User findById(int id);
    User findByLogin(String login);
    void update(User user);
    void delete(int id);
}
