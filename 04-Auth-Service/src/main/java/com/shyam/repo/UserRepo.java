package com.shyam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shyam.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	
}
