package com.shyam.dto;

import java.time.LocalDate;

import com.shyam.entity.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDto {
	private Integer orderId;
	private Integer userID;
	private Integer productId;
	private Integer quantity;
	private Double price;
	private String paymentType;
	private OrderStatus orderStatus;
	private LocalDate orderDate;
}
