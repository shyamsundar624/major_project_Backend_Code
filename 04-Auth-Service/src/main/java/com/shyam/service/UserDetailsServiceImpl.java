package com.shyam.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shyam.entity.User;
import com.shyam.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userEntity = userRepo.findByEmail(email);

		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPwd(),
				Collections.EMPTY_LIST);
	}
}
