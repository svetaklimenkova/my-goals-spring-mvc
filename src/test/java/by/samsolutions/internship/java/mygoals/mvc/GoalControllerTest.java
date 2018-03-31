package by.samsolutions.internship.java.mygoals.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class GoalControllerTest {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getGoalRequestShouldReturnGoalListPageName() throws Exception {
        // given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/goal");

        // when
        ResultActions result = mockMvc.perform(request);

        // then
        result.andExpect(MockMvcResultMatchers.view().name("goal/list"));
    }

    @Test
    public void getCreateGoalRequestShouldReturnGoalCreatePageName() throws Exception {
        // given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/goal/create");

        // when
        ResultActions result = mockMvc.perform(request);

        // then
        result.andExpect(MockMvcResultMatchers.view().name("goal/create"));
    }

    @Test
    @WithMockUser(username = "login1")
    public void getEditGoalPageWithAuthenticatedUserShouldReturnedEditPageName() throws Exception {
        // given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/goal/1/edit");

        // when
        ResultActions result = mockMvc.perform(request);

        // then
        result.andExpect(MockMvcResultMatchers.view().name("goal/edit"));
    }

    @Test
    public void getEditGoalPageWithoutAuthenticatedUserShouldReturnedAccessErrorPageName() throws Exception {
        // given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/goal/1/edit");

        // when
        ResultActions result = mockMvc.perform(request);

        // then
        result.andExpect(MockMvcResultMatchers.view().name("access_error"));
    }

    @Test
    @WithMockUser(username = "login1")
    public void getGoalPageWithIncorrectIdShouldReturnedAccessErrorPageName() throws Exception {
        // given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/goal/21");

        // when
        ResultActions result = mockMvc.perform(request);

        // then
        result.andExpect(MockMvcResultMatchers.view().name("access_error"));
    }
}