package com.shyam.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shyam.dto.CategoryDto;
import com.shyam.dto.ProductDto;

public interface ProductService {

	public ProductDto addProduct(ProductDto productDto,MultipartFile file) throws Exception;

	public ProductDto updateProduct(Integer productId,ProductDto productDto,MultipartFile file) throws Exception;
	
	public List<ProductDto> getAllProducts();

	public ProductDto getProductById(Integer id);

	public ProductDto deleteProductById(Integer productId);
	
	public boolean updateStock(Integer productId, Integer quantity);
	
}
