package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.security.model.User;
import com.security.repo.UserRepository;

@SpringBootApplication
public class SpringBootSecurityLearnApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	// P/w hume encode krke set krna hai for that we'll use BCryptPasswordEncoder
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityLearnApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setEmail("jyoti@gmail.com");
		user.setPassword(this.bCryptPasswordEncoder.encode("Jyoti@"));
		user.setUsername("Jyoti");
		user.setRole("ROLE_NORMAL");
		this.userRepository.save(user);
		
		User user1 = new User();
		user1.setEmail("subhash@gmail.com");
		user1.setPassword(this.bCryptPasswordEncoder.encode("Subhash@"));
		user1.setUsername("Subhash");
		user1.setRole("ROLE_ADMIN");
		this.userRepository.save(user1);
		
	}

}

// implements CommandLineRunner -> Jaise aap application start kronge run() chl jayenga 