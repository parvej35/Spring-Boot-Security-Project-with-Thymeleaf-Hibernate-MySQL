package com.travel.agent.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.agent.Constants;
import com.travel.agent.model.Role;
import com.travel.agent.model.User;
import com.travel.agent.repository.UserRepository;
import com.travel.agent.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(
				registrationDto.getFullname(), 
				registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), 
				Arrays.asList(new Role(Constants.CONTRIBUTOR)));
		
		return userRepository.save(user);
	}
	
	@Override
	public User saveAdmin(User user) {
		User newUser = new User(
				user.getFullname(), 
				user.getEmail(),
				passwordEncoder.encode(user.getPassword()), 
				Arrays.asList(new Role(Constants.ADMIN)));
		
		return userRepository.save(newUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findById(long id) {
		return userRepository.findById(id).get();
	}
	
}
