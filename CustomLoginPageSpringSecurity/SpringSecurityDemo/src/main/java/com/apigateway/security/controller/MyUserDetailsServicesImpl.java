package com.apigateway.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apigateway.dao.UserRepository;
import com.apigateway.entites.USER;

@Service
public class MyUserDetailsServicesImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		USER user = userRepository.findByName(username);
		if(user==null)
			throw new UsernameNotFoundException("USER NOT FOUND 404");
		
		return new UserDetailsImpl(user);
	}

	
	
}
