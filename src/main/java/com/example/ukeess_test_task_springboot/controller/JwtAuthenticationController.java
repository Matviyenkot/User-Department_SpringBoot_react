package com.example.ukeess_test_task_springboot.controller;

import com.example.ukeess_test_task_springboot.dtoModel.JwtRequest;
import com.example.ukeess_test_task_springboot.dtoModel.JwtResponse;
import com.example.ukeess_test_task_springboot.handlers.InvalidDataForRegistration;
import com.example.ukeess_test_task_springboot.handlers.InvalidDataInputException;
import com.example.ukeess_test_task_springboot.handlers.NotRegisteredUser;
import com.example.ukeess_test_task_springboot.security.JwtTokenUtil;
import com.example.ukeess_test_task_springboot.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	private String getToken(JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return token;
	}

	//return true if validation ok!
	private boolean validateRequest(JwtRequest user){
		if(user == null || user.getUsername() == null
				|| user.getUsername().length() < 2
				|| user.getPassword() == null
				|| user.getPassword().length() <= 3){
			return false;
		}
		return true;
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		if(!validateRequest(authenticationRequest))
			throw new InvalidDataInputException("You put invalid data for user");

		final String token = getToken(authenticationRequest);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> registerUser(@RequestBody JwtRequest user) throws Exception {

		if(!validateRequest(user))
			throw new InvalidDataForRegistration("You put invalid data to register");

		if(userDetailsService.registerUser(user) == 0){

			throw new NotRegisteredUser("User was not registered!");
		}
		else{
			final String token = getToken(user);

			return ResponseEntity.ok(new JwtResponse(token));
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}