package com.shyam.service;

import java.time.LocalDate;
import java.util.List;

import com.shyam.dto.OrderDto;
import com.shyam.entity.OrderStatus;

public interface ReportService {

	public List<OrderDto> getAllOrders();
	
	public List<OrderDto> orderByStatus(OrderStatus status);
	
	public List<OrderDto> getOrdersBetweenDate(LocalDate startDate, LocalDate endDate);
}
