package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Supplier;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/")
//    public String homePage(){
//        return "index";
//    }


    @GetMapping("/admin")
    public String admin(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin-create-user-form";
    }

    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
        model.addAttribute("user", user);
        return "edit-user-form";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping ("/admin/delete/{id}")
    public String deleteUser(@ModelAttribute("user") User user){
        userService.deleteUser(user);
        return "redirect:/admin";
    }


}