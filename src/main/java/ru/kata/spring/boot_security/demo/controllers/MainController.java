//package ru.kata.spring.boot_security.demo.controllers;
//
//
//
//import ru.kata.spring.boot_security.demo.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.kata.spring.boot_security.demo.entities.User;
//
//import java.security.Principal;
//
//@RestController
//public class MainController {
//    UserService userService;
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/")
//    public String homePage(){
//        return "home";
//    }
//    @GetMapping("/authenticated")
//    public String pageForAuthenticatedUser(Principal principal){
//        User user = userService.findByUsername(principal.getName());
//        return "Soobshenyi s metoda pageForAuthenticatedUser 16 stroka page controllers :" +  user.getUsername() + " " + user.getEmail();
//    }
//    @GetMapping("/read_profile")
//    public String pageForReadProfile(Principal principal){
//        return "read profile page";
//    }
//    @GetMapping("/only_for_admins")
//    public String pageOnlyForAdmins(Principal principal){
//        return "admins page";
//    }
//}
