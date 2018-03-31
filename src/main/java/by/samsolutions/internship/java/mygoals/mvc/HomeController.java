package by.samsolutions.internship.java.mygoals.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Process GET request to '/'
     * and return home page name
     *
     * @return view name
     * */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }

}