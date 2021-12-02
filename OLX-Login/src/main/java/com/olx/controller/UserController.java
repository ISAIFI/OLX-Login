package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AuthenticationRequest;
import com.olx.dto.UserDto;
import com.olx.service.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("olx/user")
public class UserController {

	@Autowired
	LoginService loginService;

	@ApiOperation(value = "Authenticate User")
	@PostMapping(value = "/authenticate", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

		return loginService.userAunthenticate(authenticationRequest);
	}

	@ApiOperation(value = "Logout User")
	@DeleteMapping(value = "/logout", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> logoutUser(@RequestHeader("auth-token") String authToken) {

		return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
	}

	@ApiOperation(value = "Register User")
	@PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {

		UserDto lUserDto = loginService.userRegistration(userDto);
		if (lUserDto == null) {
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<UserDto>(lUserDto, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "User Details")
	@GetMapping(value = "/details", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserDto> getUserDetails(@RequestHeader("auth-token") String authToken) {

		return new ResponseEntity<UserDto>(loginService.userDetails(authToken), HttpStatus.OK);
	}

	@ApiOperation(value = "Validate Token")
	@GetMapping(value = "/validate/token", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String authToken) {

		if (!loginService.validateToken(authToken)) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
	}
}
