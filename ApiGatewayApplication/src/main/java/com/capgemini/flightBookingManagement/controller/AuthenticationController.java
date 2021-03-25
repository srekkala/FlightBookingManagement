package com.capgemini.flightBookingManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.flightBookingManagement.models.AuthenticationRequest;
import com.capgemini.flightBookingManagement.models.AuthenticationResponse;
import com.capgemini.flightBookingManagement.models.UserDto;
import com.capgemini.flightBookingManagement.service.MyUserDetialsService;
import com.capgemini.flightBookingManagement.util.JwtUtilService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetialsService userDetailsService;

	@Autowired
	private JwtUtilService jwtUtil;

	@GetMapping("/")
	public String home() {
		return "<h1>Welcome</h1>";
	}

	@GetMapping("/user")
	public String user() {
		return "<h1>This is the User</h1>";
	}

	@GetMapping("/admin")
	public String admin() {
		return "<h1>This is the admin</h1>";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			System.out.println("in authentication");
			System.out.println("name:"+authenticationRequest.getUsername()+"pass:"+authenticationRequest.getPassword());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password!");
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println(userDetails);
		final String jwt = jwtUtil.generateToken(userDetails);
		System.out.println(jwt);
		System.out.println("Token generated");
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		System.out.println("in register");
		return new ResponseEntity<>(userDetailsService.save(user), HttpStatus.CREATED);
	}


}