package com.shyam.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.dto.CartDto;
import com.shyam.entity.Cart;
import com.shyam.mapper.CartMapper;
import com.shyam.repo.CartRepo;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepo cartRepo;

	@Override
	public CartDto addToCart(CartDto cartDto) {
		Cart cart = CartMapper.convertToEntity(cartDto);
		Cart savedCart = cartRepo.save(cart);
		return CartMapper.convertToDto(savedCart);
	}

	@Override
	public CartDto updateCartQuantityById(CartDto cartDto) {
		Optional<Cart> cartOp = cartRepo.findById(cartDto.getCartId());
		if (cartOp.isPresent()) {
			Cart cart = cartOp.get();
			cart.setProductId(cartDto.getProductId());
			cart.setQuantity(cartDto.getQuantity());
			cartRepo.save(cart);
			return CartMapper.convertToDto(cart);
		} else {
			throw new NoSuchElementException("Cart Not Found for Id " + cartDto.getCartId());
		}
	}

	@Override
	public CartDto getCartByUserId(Integer userid) {
		Cart cart = cartRepo.findByUserId(userid);
		if (cart != null) {
			return CartMapper.convertToDto(cart);
		} else {
			throw new NoSuchElementException("Cart Not Found By User Id " + userid);
		}
	}

	@Override
	public CartDto deleteCartById(Integer cartId) {
		Cart cart = cartRepo.findById(cartId)
				.orElseThrow(() -> new NoSuchElementException("Cart Not Found By Id " + cartId));
		cartRepo.deleteById(cartId);

		return CartMapper.convertToDto(cart);

	}

}
