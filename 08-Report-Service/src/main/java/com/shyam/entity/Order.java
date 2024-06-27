package com.shyam.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="orders")
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
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@CreationTimestamp
	private LocalDate orderDate;
}
