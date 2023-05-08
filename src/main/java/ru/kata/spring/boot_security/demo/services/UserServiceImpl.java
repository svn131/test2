package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService , UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Autowired
    public void setUserRepository(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }


    @Transactional
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        }
        return user;
    }



    public void createRole(Role role) {
        roleRepository.save(role);

    }

    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);

    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    public void deleteUser(User user) {
        userRepository.delete(user);
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

