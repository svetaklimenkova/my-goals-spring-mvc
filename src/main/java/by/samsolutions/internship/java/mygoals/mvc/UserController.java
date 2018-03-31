package by.samsolutions.internship.java.mygoals.mvc;

import by.samsolutions.internship.java.mygoals.domain.User;
import by.samsolutions.internship.java.mygoals.dto.LoginUserDto;
import by.samsolutions.internship.java.mygoals.dto.RegisterUserDto;
import by.samsolutions.internship.java.mygoals.service.MailSender;
import by.samsolutions.internship.java.mygoals.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailSender mailSender;


    /**
     * Process GET request to '/signUp' and
     * return signUp page name.
     *
     * @return view name
     * */
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String getRegisterPage() {
            return "signUp";
        }

    /**
     * Process POST request to '/register' and
     * try to register the user. After registration post
     * message to user e-mail.
     *
     * @param registerUserDto user dto
     * @param request request
     *
     * @return user dto
     * */

    @Transactional
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> getRegisterResult(
            @RequestBody RegisterUserDto registerUserDto, WebRequest request) {

        if (userService.findUserByLogin(registerUserDto.getLogin()) != null) {
            registerUserDto.setId(-1);
            return ResponseEntity.badRequest().body(registerUserDto);
        }

        User user = modelMapper.map(registerUserDto, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.create(user);

        User registeredUser = userService.findUserByLogin(user.getLogin());
        LoginUserDto userDto = modelMapper.map(registeredUser, LoginUserDto.class);

        String message = messageSource.getMessage("message.registration", null, request.getLocale());
        String email = messageSource.getMessage("email", null, request.getLocale());
        mailSender.send(email, user.getMail(), "JustDoIt", message);

        return ResponseEntity.ok(userDto);
    }

    /**
     * Process GET request to '/register' and
     * check existing of username.
     *
     * @param login username
     *
     * @return result of check (true or false)
     * */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResponseEntity<?> isValidUsername(@RequestParam("login") String login) {
        if (userService.findUserByLogin(login) != null) {
            return ResponseEntity.ok(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }
}