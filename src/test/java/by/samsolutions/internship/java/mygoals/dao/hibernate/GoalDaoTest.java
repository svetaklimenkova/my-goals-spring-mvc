package by.samsolutions.internship.java.mygoals.dao.hibernate;

import by.samsolutions.internship.java.mygoals.domain.Goal;
import by.samsolutions.internship.java.mygoals.domain.State;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class GoalDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private GoalDao goalDao;

    @Test
    public void insertNewGoalShouldAddThatGoalToDb() throws Exception {
        Date creationDate = Date.valueOf("2005-01-01");

        // given
        Goal goal = new Goal("Goal5",
                "Description4",
                creationDate,
                State.NOT_STARTED);

        // when
        goalDao.insert(goal);

        // then
        Assert.assertNotNull(goalDao.findById(goal.getId()));
        Assert.assertEquals(goal, goalDao.findById(goal.getId()));
    }

    @Test
    public void findGoalByCorrectIdShouldReturnTheGoal() throws Exception {
        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");

        // given
        int id = 1;

        // when
        Goal goal = goalDao.findById(id);

        // then
        Assert.assertNotNull(goal);
        Assert.assertEquals(1, goal.getId());
        Assert.assertEquals("Goal1", goal.getName());
        Assert.assertEquals("Description1", goal.getDescription());
        Assert.assertEquals(dataFormat.parse("2000-01-01"), goal.getCreationDate());
        Assert.assertEquals(dataFormat.parse("2001-01-01"), goal.getStartDate());
        Assert.assertEquals(dataFormat.parse("2002-01-01"), goal.getEndDate());
        Assert.assertEquals(0, goal.getState().getI());
    }

    @Test
    public void searchAllGoalsByOwnerIDShouldReturnAllOwnerGoals() throws Exception {
        // given
        int idOwner1 = 1;
        int idOwner3 = 3;

        // when
        List<Goal> goalsOwner1 = goalDao.findAllByOwnerID(idOwner1);
        List<Goal> goalsOwner3 = goalDao.findAllByOwnerID(idOwner3);

        // then
        Assert.assertEquals(1, goalsOwner1.size());
        Assert.assertEquals(2, goalsOwner3.size());
    }

    @Test
    public void updateGoalNameShouldChangeGoalName() throws Exception {
        // given
        int id = 1;

        // when
        Goal goal = goalDao.findById(id);
        goal.setName("Goal_new_1");
        goalDao.update(goal);

        // then
        Assert.assertEquals(goal, goalDao.findById(id));

        // when
        goal.setName("Goal1");
        goalDao.update(goal);

        // then
        Assert.assertEquals(goal, goalDao.findById(id));
    }

    @Test
    public void deleteGoalByIdShouldOnSearchReturnNull() throws Exception {
        // given
        int id = 2;

        // when
        goalDao.delete(id);

        // then
        Assert.assertNull(goalDao.findById(id));
    }
}