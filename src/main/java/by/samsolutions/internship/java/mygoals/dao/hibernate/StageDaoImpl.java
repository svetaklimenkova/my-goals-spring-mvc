package by.samsolutions.internship.java.mygoals.dao.hibernate;

import by.samsolutions.internship.java.mygoals.domain.Stage;
import by.samsolutions.internship.java.mygoals.domain.Task;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class StageDaoImpl extends HibernateDaoSupport implements StageDao {

    private static final String SQL_SELECT_STAGES_BY_GOAL_ID = "FROM Stage WHERE goal = :goal";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(Stage stage) {
        if (stage == null) {
            logger.error("Stage object is null.");
            return;
        }

        List<Task> tasks = stage.getTasks();

        if (tasks != null) {
            for (Task task : tasks) {
                task.setStage(stage);
            }
        }

        getHibernateTemplate().save(stage);
    }

    @Override
    public Stage findById(int id) {
        Stage stage = getHibernateTemplate().get(Stage.class, id);
        Hibernate.initialize(stage);
        return stage;
    }

    @Override
    public List<Stage> findAllByOwnerID(int idOwner) {
        Query query = sessionFactory.getCurrentSession().createQuery(SQL_SELECT_STAGES_BY_GOAL_ID);
        query.setParameter("goal", idOwner);
        return query.list();
    }

    @Override
    public void update(Stage stage) {
        if (stage == null) {
            logger.error("Stage object is null.");
            return;
        }

        List<Task> tasks = stage.getTasks();

        if (tasks != null) {
            for (Task task : tasks) {
                task.setStage(stage);
            }
        }

        getHibernateTemplate().merge(stage);
    }

    @Override
    public void delete(int id) {
        Stage stage = getHibernateTemplate().get(Stage.class, id);
        Hibernate.initialize(stage);
        getHibernateTemplate().delete(stage);
    }
}