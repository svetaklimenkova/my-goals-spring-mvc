package by.samsolutions.internship.java.mygoals.service.impl;

import by.samsolutions.internship.java.mygoals.dao.hibernate.UserDAO;
import by.samsolutions.internship.java.mygoals.domain.User;
import by.samsolutions.internship.java.mygoals.exception.ServiceException;
import by.samsolutions.internship.java.mygoals.service.UserService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    /**
     * Insert user in db
     *
     * @param user user
     *
     * @return result of inserting
     * */
    @Override
    public boolean create(User user) {
        if(user == null) {
            logger.error("User object is null.");
            return false;
        }
        userDAO.insert(user);
        return true;
    }

    /**
     * Update user by
     *
     * @param user user
     *
     * @return result of updating
     * */
    @Override
    public boolean update(User user) {
        if(user == null) {
            logger.error("User object is null.");
            return false;
        }

        userDAO.update(user);
        return true;
    }

    /**
     * Search user by username
     *
     * @param login username
     *
     * @return user
     * */
    @Override
    public User findUserByLogin(String login) {
        if(login == null) {
            throw new ServiceException("Login is null.");
        }

        User user = null;
        try {
            user = userDAO.findByLogin(login);
            if (user != null) {
                Hibernate.initialize(user.getGoals());
            }
        } catch (EmptyResultDataAccessException e) {
            logger.error("User with login = " + login + " didn't found.");
        }

        return user;
    }

    /**
     * Delete user by user id
     *
     * @param idUser user id
     *
     * @return result of deleting
     * */
    @Override
    public boolean delete(int idUser) {
        if (userDAO.findById(idUser) == null) {
            logger.error("User object with id = " + idUser + " doesn't exist");
            return false;
        }
        userDAO.delete(idUser);
        return true;
    }

    /**
     * Search user by user id
     *
     * @param id user id
     *
     * @return user
     * */
    @Override
    public User findUserByID(int id) {
        User user = null;
        try {
            user = userDAO.findById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("User object with id = " + id + "don't found");
        }
        return user;
    }
}
