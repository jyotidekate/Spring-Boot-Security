package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.model.CustomUserDetails;
import com.security.model.User;
import com.security.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("NO USER");
		}
		return new CustomUserDetails(user);
	}

}

// From this ' username ' data will get load from DB 
// User load krne  ke lia we need repository 
// Now we'll use this CustomUserDetailsService in Configuration ie.,MySecurityConfig.java