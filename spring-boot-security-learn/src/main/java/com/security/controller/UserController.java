package com.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.model.User;
import com.security.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	// all users
	@GetMapping("/")
	public List<User> getAllUsers(){
		return this.userService.getAllUsers();
	}
	
	// return single user
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}
	
	@PostMapping("/")
	public User add(@RequestBody User user) {
		return this.userService.addUser(user);
	}
	
}

// @RestController -> jo data return hoga it'll be in JSON (page view ka koi concept nahi hai)
// @PathVariable -> ye url ki value ko fetch krenga ie., ("/{username}") than it'll pass/ put this to here 
// (@PathVariable("username") String username)
// Jab bhi data add hota hai we'll use @PostMapping
