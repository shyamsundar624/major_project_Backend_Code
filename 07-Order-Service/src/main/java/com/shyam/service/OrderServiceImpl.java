package com.shyam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.shyam.dto.ProductOrderDto;
import com.shyam.entity.Order;
import com.shyam.mapper.OrderMapper;
import com.shyam.repo.OderRepository;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OderRepository orderRepo;

	@Override
	public List<ProductOrderDto> addOrder(List<ProductOrderDto> productOrders) {
		List<Order> orderEntities = productOrders.stream().map(OrderMapper::convertToEntity)
				.collect(Collectors.toList());
		List<Order> orders = orderRepo.saveAll(orderEntities);

		return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public ProductOrderDto updateOrder(ProductOrderDto productOrderDto) {
		Order orderEntity = OrderMapper.convertToEntity(productOrderDto);

		Order order = orderRepo.findById(orderEntity.getOrderId()).orElseThrow();

		if (order != null) {
			order.setProductId(productOrderDto.getProductId());
			order.setPrice(productOrderDto.getPrice());
			order.setQuantity(productOrderDto.getQuantity());
			order.setPaymentType(productOrderDto.getPaymentType());
			order.setOrderDate(productOrderDto.getOrderDate());
			order.setOrderStatus(productOrderDto.getOrderStatus());

			Order updatedOrder = orderRepo.save(order);
			return OrderMapper.convertToDto(updatedOrder);
		} else {
			return null;
		}
	}

	@Override
	public List<ProductOrderDto> getOrdersByUserId(Integer userId) {
		List<Order> orders = orderRepo.findByUserId(userId);

		if (orders != null && !orders.isEmpty()) {
			return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
		} else {
			return null;
		}

	}

	@Override
	public List<ProductOrderDto> getOrderByDateAndStatus(String orderDate, String orderStatus) {
		List<Order> orders = orderRepo.findByOrderDateAndOrderStatus(orderDate, orderStatus);

		if (orders != null && !orders.isEmpty()) {
			return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
		} else {
			return null;
		}
	}

	@Override
	public List<ProductOrderDto> getAllOrders() {
		return orderRepo.findAll().stream().map(OrderMapper:: convertToDto).collect(Collectors.toList());
	}

}
