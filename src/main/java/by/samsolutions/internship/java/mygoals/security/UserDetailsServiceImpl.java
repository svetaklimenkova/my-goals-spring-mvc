package by.samsolutions.internship.java.mygoals.security;

import by.samsolutions.internship.java.mygoals.domain.User;
import by.samsolutions.internship.java.mygoals.exception.ServiceException;
import by.samsolutions.internship.java.mygoals.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsServiceImpl implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if(login == null) {
            throw new ServiceException("Login is null.");
        }

        User user;
        try {
            user = userService.findUserByLogin(login);
        } catch (EmptyResultDataAccessException e) {
            logger.error("User with login " + login + " didn't found.");
            throw new UsernameNotFoundException("User with login = " + login + " didn't found.");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), getAuthorities());
    }

    private Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authList;
    }
}
