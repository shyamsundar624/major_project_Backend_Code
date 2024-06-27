
package com.shyam.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {

	private Integer productId;
	private String productName;
	private String desc;
	private Double price;
	private Integer stock;
	private String image;
	private Integer discount;
	private Double priceBeforeDiscount;

	private Integer categoryId;
}
