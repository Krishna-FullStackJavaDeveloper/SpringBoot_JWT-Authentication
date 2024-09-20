package com.jwt.app.service;

import org.springframework.stereotype.Service;

import com.jwt.app.dtos.LoginUserDto;
import com.jwt.app.dtos.RegisterUserDto;
import com.jwt.app.entities.User;
import com.jwt.app.repositories.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
	
	  private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final AuthenticationManager authenticationManager;

	    public AuthenticationService(
	        UserRepository userRepository,
	        AuthenticationManager authenticationManager,
	        PasswordEncoder passwordEncoder
	    ) {
	        this.authenticationManager = authenticationManager;
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

	    public User signup(RegisterUserDto input) {
	        var user = new User().setFullName(input.getFullName()).setEmail(input.getEmail()).setPassword(passwordEncoder.encode(input.getPassword()));

	        return userRepository.save(user);
	    }

	    public User authenticate(LoginUserDto input) {
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                input.getEmail(),
	                input.getPassword()
	            )
	        );

	        return userRepository.findByEmail(input.getEmail()).orElseThrow();
	    }

	    public List<User> allUsers() {
	        List<User> users = new ArrayList<>();

	        userRepository.findAll().forEach(users::add);

	        return users;
	    }

}
