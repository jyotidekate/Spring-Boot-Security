package com.security.model;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
	// From where we get p/w cause p/w to user ke pass hai to get p/w hume yaha user ka data lana hoga through constructor 
	private User user;
	
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}	// Jab hum CustomUserDetails ka object create krenge to hum isme User ka object pass kr denge 

	// From here basically we have to return it's authority 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<SimpleGrantedAuthority> set = new HashSet<SimpleGrantedAuthority>();
		set.add(new SimpleGrantedAuthority(this.user.getRole()));
		return set;	
		// Abhi humare pass ek hi role hai to simple hum role pass kr denge like above line otherwise if we have
		// Lots of roles that hume loop chalake tab add krna hota hai
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}	// true cause na hum expired hai na hi hum locked hai cause we have not created any variables for this

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
