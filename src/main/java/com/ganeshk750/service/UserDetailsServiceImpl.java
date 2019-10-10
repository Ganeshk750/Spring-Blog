package com.ganeshk750.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ganeshk750.model.User;
import com.ganeshk750.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	User user = userRepository.findByUserName(username)
		.orElseThrow(()-> new UsernameNotFoundException("No User Found"+ username));
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthorities("ROLE_USER"));
	}


	private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
	   return Collections.singletonList(new SimpleGrantedAuthority(role_user))	;
	}

}
