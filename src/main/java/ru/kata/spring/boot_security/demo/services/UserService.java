package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.core.userdetails.UserDetails;

import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;



import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByUsername(String username);
    UserDetails loadUserByEmail(String email);
    void createRole(Role role);
    Role findRoleByName(String roleName);
    void createUser(User user);
    List<User> findAll();
    User save(User user);
    Optional<User> findById(Long id);
    void deleteUser(User user) ;
    User findByEmail(String email);

    }
