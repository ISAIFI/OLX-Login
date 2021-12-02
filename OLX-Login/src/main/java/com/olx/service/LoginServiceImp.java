package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.olx.dto.AuthenticationRequest;
import com.olx.dto.UserDto;
import com.olx.entity.UserEntity;
import com.olx.repo.UserRepo;

@Service
public class LoginServiceImp implements LoginService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	LoginService loginService;

	
	@Override
	public UserDto userRegistration(UserDto user) {
	
		UserEntity userEntity = getUserEntityFromDto(user);
		userEntity = userRepo.save(userEntity);
		return getUserDtoFromEntity(userEntity);
	}

	@Override
	public Boolean userLogout(String authToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto userDetails(String authToken) {

		if (validateToken(authToken) == true) {
			String jwtToken = authToken.substring(7, authToken.length());
			String userName = jwtUtil.extractUsername(jwtToken);			
			return getUserDtoListFromEntityList(userRepo.findByUsername(userName)).get(0);
		}else {
			return null;
		}
	}
	
	
	private UserDto getUserDtoFromEntity(UserEntity userEntity) {
		
		UserDto userDto = this.modelMapper.map(userEntity, UserDto.class);
		return userDto;
	}

	private UserEntity getUserEntityFromDto(UserDto userDto) {
		
		UserEntity userEntity = this.modelMapper.map(userDto, UserEntity.class);
		return userEntity;
	}
	
	private List<UserDto> getUserDtoListFromEntityList(List<UserEntity> stockEntityList) {
		// TODO Auto-generated method stub
		List<UserDto> stockList = new ArrayList<>();
		
		for (UserEntity stockEntity : stockEntityList) {
			
			stockList.add(getUserDtoFromEntity(stockEntity));
		}
		return stockList;
	}

	@Override
	public ResponseEntity<String> userAunthenticate(AuthenticationRequest authenticationRequest) {
		
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		} catch (BadCredentialsException e) { // Login Failed
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		// Login Successful
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String jwtToken = jwtUtil.generateToken(userDetails);

		return new ResponseEntity<String>(jwtToken, HttpStatus.BAD_REQUEST);
	}

	@Override
	public Boolean validateToken(String authToken) {
		
		boolean isValidToken = false;
		try {
			String jwtToken = authToken.substring(7, authToken.length());
			String userName = jwtUtil.extractUsername(jwtToken);
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			isValidToken = jwtUtil.validateToken(jwtToken, userDetails);

		} catch (Exception e) {
			return isValidToken;
		}
		return isValidToken;

	}


}
