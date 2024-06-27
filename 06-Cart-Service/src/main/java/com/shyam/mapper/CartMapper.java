package com.shyam.mapper;

import org.modelmapper.ModelMapper;

import com.shyam.dto.CartDto;
import com.shyam.entity.Cart;

public class CartMapper {
	private static final ModelMapper mapper = new ModelMapper();

	public static CartDto convertToDto(Cart cart) {
		return mapper.map(cart, CartDto.class);
	}

	public static Cart convertToEntity(CartDto cartDto) {
		return mapper.map(mapper, Cart.class);
	}
}
