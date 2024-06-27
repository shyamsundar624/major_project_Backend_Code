package com.shyam.mapper;

import org.modelmapper.ModelMapper;

import com.shyam.dto.ProductOrderDto;
import com.shyam.entity.Order;

public class OrderMapper {
private static final ModelMapper mapper=new ModelMapper();

public static ProductOrderDto convertToDto(Order order) {
	return mapper.map(order, ProductOrderDto.class);
}

public static Order convertToEntity(ProductOrderDto productOrderDto) {
	return mapper.map(productOrderDto, Order.class);
}
}
