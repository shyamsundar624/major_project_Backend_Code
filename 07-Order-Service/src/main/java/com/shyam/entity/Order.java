package com.shyam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private Integer userID;
	private Integer productId;
	private Integer quantity;
	private Double price;
	private String paymentType;
	private String orderStatus;
	private String orderDate;
}
