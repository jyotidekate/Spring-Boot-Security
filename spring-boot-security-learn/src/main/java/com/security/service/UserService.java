package com.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.security.model.User;

@Service
public class UserService {

	List<User> list = new ArrayList<User>(); // this data is not connected to DB

	public UserService() {
		list.add(new User("abc", "abc", "abc@gmail.com"));
		list.add(new User("xyz", "xyz", "xyz@gmail.com"));
	}
	
	// get all users
	public List<User> getAllUsers(){
		return this.list;
	}
	
	// get single user
	public User getUser(String username) {
		return this.list.stream().filter((user) -> user.getUsername().equals(username)).findAny().orElse(null);
	}
	
	// add new user
	public User addUser(User user) {
		this.list.add(user);
		return user;
	}
	
}

// We are making it service so that hum isko autowired kr paye.
// Service lagane ka matlab ye hai ki basically hume spring component bana diya hai to iske object ko hum log autowired kr skte hai.