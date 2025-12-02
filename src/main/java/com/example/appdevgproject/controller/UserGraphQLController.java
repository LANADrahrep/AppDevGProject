package com.example.appdevgproject.controller;

import com.example.appdevgproject.entity.Role;
import com.example.appdevgproject.entity.User;
import com.example.appdevgproject.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UserGraphQLController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserGraphQLController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public User userById(@Argument Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new java.util.NoSuchElementException("User not found with id " + id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public User createUser(@Argument String username,
                           @Argument String password,
                           @Argument Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setEnabled(true);
        return userRepository.save(user);
    }
}