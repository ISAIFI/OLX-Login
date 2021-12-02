package com.olx.service;

import org.springframework.http.ResponseEntity;

import com.olx.dto.AuthenticationRequest;
import com.olx.dto.UserDto;

public interface LoginService {

	public UserDto userRegistration(UserDto user);
	public Boolean userLogout(String authToken);
	public UserDto userDetails(String authToken);
	public ResponseEntity<String> userAunthenticate(AuthenticationRequest authenticationRequest);
	public Boolean validateToken(String authToken);
}
