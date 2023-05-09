package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User getUserById(long id) ;

    List<User> getListOfUsers();

    @Transactional
    void deleteUser(Long id);

    User findByUsername(String email);

    void saveUser(User user);

    UserDetails  loadUserByEmail (String username);

    void createRole(Role role);

    Role findRoleByName(String roleName);

    void createUser(User user);

    User findByEmail(String email);
}
