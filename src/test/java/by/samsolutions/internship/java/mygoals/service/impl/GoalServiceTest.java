package by.samsolutions.internship.java.mygoals.service.impl;

import by.samsolutions.internship.java.mygoals.domain.Goal;
import by.samsolutions.internship.java.mygoals.service.GoalService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class GoalServiceTest {

    @Autowired
    private GoalService goalService;

    @Test
    public void findGoalByIncorrectIdShouldReturnNull() throws Exception {
        // given
        int incorrectId = 10;

        // when
        Goal goal = goalService.findById(incorrectId);

        // then
        Assert.assertNull(goal);
    }

    @Test
    public void searchAllGoalsByIncorrectOwnerIdShouldReturnNullSizeList() throws Exception {
        // given
        int idNonexistentOwner = 10;

        // when
        List<Goal> goals = goalService.findAllByOwnerID(idNonexistentOwner);

        // then
        Assert.assertEquals(0, goals.size());
    }

    @Test
    public void searchGoalByCorrectIdShouldReturnGoalWithUser() throws Exception {
        // given
        int id = 1;

        // when
        Goal goal = goalService.findById(id);

        // then
        Assert.assertNotNull(goal.getUser());
    }
}