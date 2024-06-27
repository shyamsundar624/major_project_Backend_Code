package com.shyam.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.shyam.dto.OrderDto;
import com.shyam.entity.Order;

@Component
public class OrderMapper {

	private static final ModelMapper mapper=new ModelMapper();
	
	public static OrderDto convertToDto(Order order) {
		return mapper.map(order, OrderDto.class);
	}
	public static Order convertToEntity(OrderDto dto) {
		return mapper.map(dto, Order.class);
	}
}
