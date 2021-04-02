package com.cg.flightBookingManagement.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.flightBookingManagement.dao.UserDao;
import com.cg.flightBookingManagement.entities.MyUserDetails;
import com.cg.flightBookingManagement.entities.User;
import com.cg.flightBookingManagement.models.UserDto;

@Service
public class MyUserDetialsService implements UserDetailsService{

	@Autowired
	private UserDao userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("in service");
		User user = userRepo.findByUserName(username);
		System.out.println("details from repo:"+user);
		if (user==null) {
			throw new UsernameNotFoundException(username + " You are Not Registered. Please Register First");
		}
		UserDetails userDetails=(new MyUserDetails(user));
		System.out.println("userdetails:"+userDetails);
		return userDetails;
		
	}

	//Registering a new User
	public User save(UserDto user) {
		User newUser = new User();
		newUser.setActive(user.getActive());
		newUser.setPassword(user.getPassword());
		newUser.setUserName(user.getUserName());
		newUser.setRoles(user.getRoles());
		userRepo.save(newUser);
		return newUser;
		}
}


