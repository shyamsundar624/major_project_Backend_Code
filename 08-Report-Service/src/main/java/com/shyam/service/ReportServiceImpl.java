package com.shyam.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.shyam.dto.OrderDto;
import com.shyam.entity.Order;
import com.shyam.entity.OrderStatus;
import com.shyam.mapper.OrderMapper;
import com.shyam.repository.OrderRepository;

public class ReportServiceImpl implements ReportService {

	@Autowired
	private OrderRepository orderRepo;

	@Override
	public List<OrderDto> getAllOrders() {
		List<Order> orders = orderRepo.findAll();
		return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> orderByStatus(OrderStatus status) {
		List<Order> byStatus = orderRepo.findByStatus(status);

		return byStatus.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> getOrdersBetweenDate(LocalDate startDate, LocalDate endDate) {
		List<Order> dates = orderRepo.findByOrderBetweenDates(startDate, endDate);
		return dates.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	}

}
