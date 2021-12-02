package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olx.entity.UserEntity;
import com.olx.repo.UserRepo;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	UserRepo userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserEntity> userEntityList = this.userRepo.findByUsername(username);
		if(userEntityList.size() == 0) {
			throw new UsernameNotFoundException(username);
		}
		
		UserEntity userEntity = userEntityList.get(0);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userEntity.getRoles()));
		User user = new User(userEntity.getUsername(), passwordEncoder.encode(userEntity.getPassword()), authorities);
		return user;
	}

	
}
