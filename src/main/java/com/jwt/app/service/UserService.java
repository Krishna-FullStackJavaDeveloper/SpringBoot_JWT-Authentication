package com.jwt.app.service;

import org.springframework.stereotype.Service;

import com.jwt.app.entities.User;
import com.jwt.app.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
