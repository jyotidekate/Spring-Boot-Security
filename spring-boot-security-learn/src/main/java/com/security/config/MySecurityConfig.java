package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.security.service.CustomUserDetailsService;

import ch.qos.logback.core.joran.action.NOPAction;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http); -> we'll remove this cause we'll write our own configuration
		// Basically we are doing method chaining
		http
		.authorizeRequests()		// that's means ki hume request ko authorized krna hai
		.anyRequest()				// koi bhi request aari hai usko authenticate krdo
		.authenticated()
		.and()
		.httpBasic();
		// matlab ki hume request ko authorized krna hai, koi bhi request ho sab ko authorized krdo aur authenticated ho matlab jo bhi 
		// permission access krne hai to phle wo authenticated hogi ie., check hogi or hum jo mechainsm use krenge wo basic 
		// authentication hai
	}*/
	
	// Refer HomeController class
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http); 
		http
		//.csrf().disable()
		.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
		.authorizeRequests()	
		//.antMatchers("/home").permitAll()
		//.antMatchers("/home", "/login", "/register").permitAll()
		//.antMatchers("/public/**").permitAll()
		//.antMatchers(HttpMethod.GET, "/public/**").permitAll()
		.antMatchers("/public/**").hasRole("NORMAL") 			// Humara jo public hai wo normal users hi use krenge not admin
		.antMatchers("/users/**").hasRole("ADMIN") 
		.anyRequest()				
		.authenticated()
		.and()
		.httpBasic();
		// Yaha humne kaha hai ki sare request ko authenticate kr do 
		// authorizeRequests to karo after that i'll use antMatchers("/home") inside that hum url bata skte hai ki humara jo url hai 
		// ie., /home usko hum permitAll kr denge matlab iss /home se matching hone wali chizo ko hum logo ne permission de di hai 
		// & home ke alava koi request hoge to wo authenticate hogi 
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http); 
		http
		//.csrf().disable()
		.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
		.authorizeRequests()	
		//.antMatchers("/home").permitAll()
		//.antMatchers("/home", "/login", "/register").permitAll()
		//.antMatchers("/public/**").permitAll()
		//.antMatchers(HttpMethod.GET, "/public/**").permitAll()
		.antMatchers("/signin").permitAll()						// making this url as a public
		.antMatchers("/public/**").hasRole("NORMAL") 			// Humara jo public hai wo normal users hi use krenge not admin
		.antMatchers("/users/**").hasRole("ADMIN") 
		.anyRequest()				
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/signin")	         // here you are telling your page ie., jo login hai usko sign in krenge // ie., ab jo apka login in ka page hai ab wo sign in ka page hai
		.loginProcessingUrl("/dologin")	// To iss page pr login hoga
		.defaultSuccessUrl("/users/");    // Jab login successful ho jayengi to ye users pr hi redirect krnega through defaultSuccessUrl
		
	}

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		// We are using inMemoryAuthentication -
		// 1 user is created & you can create multiple user also
		//auth.inMemoryAuthentication().withUser("John").password("john@123").roles("NORMAL");	
		//auth.inMemoryAuthentication().withUser("Jyoti").password("jyoti@123").roles("ADMIN");
		// Admin ke pass jada power hogi & normal user only can view where admin can read, write, etc
		
		auth.inMemoryAuthentication().withUser("John").password(this.passwordEncoder().encode("john@123")).roles("NORMAL");	
		auth.inMemoryAuthentication().withUser("Jyoti").password(this.passwordEncoder().encode("jyoti@123")).roles("ADMIN");	
	}*/
	
	// In above method humne waha pr users banaye the phle 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	/*
	@Bean
	public PasswordEncoder passwordEncoder() {
		//return NoOpPasswordEncoder.getInstance(); // it'll return the instance of NoOpPasswordEncoder
		return new BCryptPasswordEncoder(10);  		// here we are passing a strength of encoding ie., 10
	}
	// Matlab aap koi PasswordEncoder use nahi krre ho, aap plain text ko as a PasswordEncoder use krre ho, jo ki production level pr
	// use nahi krte hai 
	*/
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

}

// Ye humari configuration class hai main & than you'll use @EnableWebSecurity
// Hume abhi apna authentication mechanism configure krna hai kyu ki hume by default form based authentication milra hai to hum log
// usko change krenge in basic authentication for that we'll override configure(HttpSecurity http)
