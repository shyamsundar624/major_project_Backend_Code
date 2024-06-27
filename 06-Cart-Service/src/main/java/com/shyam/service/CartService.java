package com.shyam.service;

import com.shyam.dto.CartDto;

public interface CartService {

	public CartDto addToCart(CartDto cartDto);
	
	public CartDto updateCartQuantityById(CartDto cartDto);
	
	public CartDto getCartByUserId(Integer userid);
	
	public CartDto deleteCartById(Integer cartId);
}
