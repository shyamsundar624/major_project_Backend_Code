package com.shyam.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.constant.AppConstant;
import com.shyam.dto.CartDto;
import com.shyam.props.AppProps;
import com.shyam.response.ApiResponse;
import com.shyam.service.CartService;

@RestController
public class CartRestController {

	@Autowired
	private CartService cartService;

	@Autowired
	private AppProps appProps;

	@PostMapping("/addCart")
	public ResponseEntity<ApiResponse<CartDto>> addCart(@RequestBody CartDto cartDto) {
		ApiResponse<CartDto> response = new ApiResponse<>();
		Map<String, String> message = appProps.getMessage();
		CartDto addCart = cartService.addToCart(cartDto);
		if (addCart != null) {
			response.setStatusCode(200);
			response.setMessage(message.get(AppConstant.CART_ADDED_SUCESS));
			response.setData(addCart);
		} else {
			response.setStatusCode(500);
			response.setMessage(message.get(AppConstant.CART_ADD_ERR));
		}
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PutMapping("/updateCart")
	public ResponseEntity<ApiResponse<CartDto>> updateCartByUserId(@RequestBody CartDto cartDto) {
		CartDto updateCart = cartService.updateCartQuantityById(cartDto);
		ApiResponse<CartDto> response = new ApiResponse<>();
		Map<String, String> message = appProps.getMessage();

		if (updateCart != null) {
			response.setStatusCode(200);
			response.setMessage(message.get(AppConstant.CART_UPDATE_SUCESS));
			response.setData(updateCart);
		} else {
			response.setStatusCode(500);
			response.setMessage(message.get(AppConstant.CART_UPDATE_ERR));
		}
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("/cart/{id}")
	public ResponseEntity<ApiResponse<CartDto>> getCartByUserId(@PathVariable("id") Integer userId) {
		CartDto cartDto = cartService.getCartByUserId(userId);
		ApiResponse<CartDto> response = new ApiResponse<>();
		Map<String, String> message = appProps.getMessage();

		if (cartDto != null) {
			response.setStatusCode(200);
			response.setMessage(message.get(AppConstant.CART_RETRIEVE_SUCESS));
			response.setData(cartDto);
		} else {
			response.setStatusCode(500);
			response.setMessage(message.get(AppConstant.CART_RETRIEVE_ERR));
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/deletecart/{id}")
	public ResponseEntity<ApiResponse<String>> deleteCartById(@PathVariable("id") Integer cartId) {
		ApiResponse<String> response = new ApiResponse<>();
		Map<String, String> message = appProps.getMessage();
		CartDto cartDto = cartService.deleteCartById(cartId);
		response.setStatusCode(200);
		response.setMessage(message.get(AppConstant.CART_DELETE_SUCESS));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
