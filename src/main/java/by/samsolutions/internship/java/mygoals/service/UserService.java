package by.samsolutions.internship.java.mygoals.service;

import by.samsolutions.internship.java.mygoals.domain.User;

public interface UserService {
    boolean create(User user);
    boolean update(User user);
    User findUserByLogin(String login);
    boolean delete(int idUser);
    User findUserByID(int id);
}
