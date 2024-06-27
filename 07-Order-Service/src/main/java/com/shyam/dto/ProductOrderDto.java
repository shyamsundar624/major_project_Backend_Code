package com.shyam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderDto {

	private Integer orderId;
	private Integer userID;
	private Integer productId;
	private Integer quantity;
	private Double price;
	private String paymentType;
	private String orderStatus;
	private String orderDate;
}
