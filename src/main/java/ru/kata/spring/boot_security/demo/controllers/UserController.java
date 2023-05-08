package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

@Controller
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = (BCryptPasswordEncoder)passwordEncoder;
    }

    @GetMapping("/user")
    public String user(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "user";
    }
}

