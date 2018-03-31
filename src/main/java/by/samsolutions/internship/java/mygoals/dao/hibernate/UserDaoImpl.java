package by.samsolutions.internship.java.mygoals.dao.hibernate;

import by.samsolutions.internship.java.mygoals.domain.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDAO {

    private static final String SQL_SELECT_BY_LOGIN = "FROM User WHERE login = ?";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(User user) {
        getHibernateTemplate().save(user);
    }

    @Override
    public User findById(int id) {
        User user = getHibernateTemplate().get(User.class, id);
        Hibernate.initialize(user.getGoals());
        return user;
    }

    @Override
    public User findByLogin(String login) {
        List users = getHibernateTemplate().find(SQL_SELECT_BY_LOGIN, login);
        if (users.size() == 0) {
            return null;
        } else {
            return (User) users.get(0);
        }

    }

    @Override
    public void update(User user) {
        getHibernateTemplate().update(user);
    }

    @Override
    public void delete(int id) {
        User user = getHibernateTemplate().get(User.class, id);
        getHibernateTemplate().delete(user);
    }
}