package by.samsolutions.internship.java.mygoals.dao.hibernate;

import by.samsolutions.internship.java.mygoals.domain.Task;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class TaskDaoImpl extends HibernateDaoSupport implements TaskDao {

    private static final String SQL_SELECT_TASKS_BY_STAGE_ID = "FROM Task WHERE stage = :stage";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(Task task) {
        getHibernateTemplate().save(task);
    }

    @Override
    public Task findById(int id) {
        Task task = getHibernateTemplate().get(Task.class, id);
        Hibernate.initialize(task);
        return task;
    }

    @Override
    public List<Task> findAllByOwnerID(int idOwner) {
        Query query = sessionFactory.getCurrentSession().createQuery(SQL_SELECT_TASKS_BY_STAGE_ID);
        query.setParameter("owner", idOwner);
        return query.list();
    }

    @Override
    public void update(Task task) {
        getHibernateTemplate().merge(task);
    }

    @Override
    public void delete(int id) {
        Task task = getHibernateTemplate().get(Task.class, id);
        Hibernate.initialize(task);
        getHibernateTemplate().delete(task);
    }
}