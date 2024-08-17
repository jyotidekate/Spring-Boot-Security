package com.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String username);

}

//Repository interract with DB
// By using this we can insert data into DB
