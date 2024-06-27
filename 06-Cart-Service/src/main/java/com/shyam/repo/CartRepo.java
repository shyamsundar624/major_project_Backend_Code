package com.shyam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shyam.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{

	public Cart findByUserId(Integer userId);
}
