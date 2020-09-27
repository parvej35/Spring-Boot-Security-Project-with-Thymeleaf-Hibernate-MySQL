package com.travel.agent.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.travel.agent.model.User;
import com.travel.agent.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	
	User findById(long id);
	
	User findByEmail(String email);
	
	User save(UserRegistrationDto registrationDto);

	User saveAdmin(User user);
}
