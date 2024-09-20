package com.jwt.app.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import com.jwt.app.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	 Optional<User> findByEmail(String email);

	
}
