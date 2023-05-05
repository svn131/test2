package ru.kata.spring.boot_security.demo.configs;

import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public DataInitializer(PasswordEncoder passwordEncoder, UserService userService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        createUserIfNotFound("user","user", "user",22L,"1111", "john.doe@example.com", Arrays.asList("ROLE_USER"));
        createUserIfNotFound("admin","admin", "admin",22L, "1111", "admin@example.com", Arrays.asList("ROLE_ADMIN"));

        alreadySetup = true;
    }

    @Transactional
    void createRoleIfNotFound(String name) {
        Role role = userService.findRoleByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            userService.createRole(role);
        }
    }

    @Transactional
    void createUserIfNotFound(String userNahuy,String lastname,String firstName,Long age, String password, String email, List<String> roles) {
        User user = userService.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setLastName(lastname);
            user.setFirstName(firstName);
            user.setAge(age);

            user.setUsername(userNahuy);


            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            List<Role> userRoles = new ArrayList<>();
            for (String roleName : roles) {
                Role role = userService.findRoleByName(roleName);
                if (role != null) {
                    userRoles.add(role);
                }
            }
            user.setRoles(userRoles);
            userService.createUser(user);
        }
    }
}


