package com.shyam.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;

	private String categoryName;

	private String categoryImage;

	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<Product> products;
	
	@CreationTimestamp
	@Column(name = "create_dt", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "update_dt", insertable = false)
	private Date updatedDate;
	
	
}
