package com.shyam.service;

import java.util.List;

import com.shyam.dto.ProductOrderDto;

public interface OrderService {

	public List<ProductOrderDto> addOrder(List<ProductOrderDto> productOrders);
	
	public ProductOrderDto updateOrder(ProductOrderDto productOrderDto);
	
	public List<ProductOrderDto> getOrdersByUserId(Integer userId);
	
	public List<ProductOrderDto> getOrderByDateAndStatus(String orderDate,String orderStatus);
	
	public List<ProductOrderDto> getAllOrders();
}
