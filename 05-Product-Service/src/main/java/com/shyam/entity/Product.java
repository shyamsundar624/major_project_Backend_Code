package com.shyam.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String productName;
	private String desc;
	private Double price;
	private Integer stock;
	private String image;
	private Integer discount;
	private Double priceBeforeDiscount;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@CreationTimestamp
	@Column(name = "create_dt", updatable = false)
	private Date createdDate;
	@UpdateTimestamp
	@Column(name = "update_dt", insertable = false)
	private Date updatedDate;
}
