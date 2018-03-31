package by.samsolutions.internship.java.mygoals.dao.hibernate;

import by.samsolutions.internship.java.mygoals.domain.Goal;
import by.samsolutions.internship.java.mygoals.domain.Stage;
import by.samsolutions.internship.java.mygoals.domain.Task;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class GoalDaoImpl extends HibernateDaoSupport implements GoalDao {

    private static final String SQL_SELECT_LAST_GOAL = "FROM Goal WHERE owner = :owner";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(Goal goal) {
        List<Stage> stages = goal.getStages();

        if (stages != null) {
            for (Stage stage : stages) {
                stage.setGoal(goal);
                for (Task task : stage.getTasks()) {
                    task.setStage(stage);
                }
            }
        }

        getHibernateTemplate().save(goal);
    }

    @Override
    public Goal findById(int id) {
        Goal goal = getHibernateTemplate().get(Goal.class, id);
        Hibernate.initialize(goal);
        return goal;
    }

    @Override
    public List<Goal> findAllByOwnerID(int idOwner) {
        Query query = sessionFactory.getCurrentSession().createQuery(SQL_SELECT_LAST_GOAL);
        query.setParameter("owner", idOwner);
        List list = query.list();
        return list;
    }

    @Override
    public void update(Goal goal) {
        List<Stage> stages = goal.getStages();
        for (Stage stage : stages) {
            if (stage.getGoal() == null) {
                stage.setGoal(goal);
            }
            if (stage.getTasks() == null && stage.getTasks().size() == 0) {
                continue;
            }
            for (Task task : stage.getTasks()) {
                task.setStage(stage);
            }
        }

        getHibernateTemplate().merge(goal);
    }

    @Override
    public void delete(int id) {
        Goal goal = getHibernateTemplate().get(Goal.class, id);
        Hibernate.initialize(goal);
        getHibernateTemplate().delete(goal);
    }
}