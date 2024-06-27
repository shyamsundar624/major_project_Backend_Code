package com.shyam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shyam.constant.AppConstant;
import com.shyam.dto.ProductDto;
import com.shyam.entity.Product;
import com.shyam.exception.ProductServiceEception;
import com.shyam.mapper.ProductMapper;
import com.shyam.repo.CategoryRepo;
import com.shyam.repo.ProductRepo;
import com.shyam.util.FileUtils;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public ProductDto addProduct(ProductDto productDto, MultipartFile file) throws Exception {
		String fileCode = FileUtils.saveFile(file.getName(), file);
		Product product = ProductMapper.convertToEntity(productDto);
		product.setImage(fileCode);
		Product saveProduct = productRepo.save(product);

		return ProductMapper.convertToDto(saveProduct);
	}

	@Override
	public ProductDto updateProduct(Integer productId, ProductDto productDto, MultipartFile file) throws Exception {
		String fileCode = FileUtils.saveFile(file.getName(), file);
		Product existingProduct = productRepo.findById(productId)
				.orElseThrow(() -> new ProductServiceEception(AppConstant.CATEGORY_NOT_FOUND,
						AppConstant.CATEGORY_NOT_FOUND_ERR_CD));
		existingProduct.setImage(fileCode);
		existingProduct.setProductName(productDto.getProductName());
		existingProduct.setDesc(productDto.getDesc());
		existingProduct.setPrice(productDto.getPrice());
		existingProduct.setStock(productDto.getStock());
		existingProduct.setDiscount(productDto.getDiscount());
		existingProduct.setPriceBeforeDiscount(productDto.getPriceBeforeDiscount());
		existingProduct.setCategory(categoryRepo.findById(productDto.getCategoryId()).get());

		Product updatedProduct = productRepo.save(existingProduct);

		return ProductMapper.convertToDto(updatedProduct);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepo.findAll();

		return products.stream().map(ProductMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductById(Integer id) {
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ProductServiceEception(AppConstant.CATEGORY_NOT_FOUND,
						AppConstant.CATEGORY_NOT_FOUND_ERR_CD));
		return ProductMapper.convertToDto(product);
	}

	@Override
	public ProductDto deleteProductById(Integer productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ProductServiceEception(AppConstant.CATEGORY_NOT_FOUND,
						AppConstant.CATEGORY_NOT_FOUND_ERR_CD));
		productRepo.delete(product);
		return ProductMapper.convertToDto(product);
	}

	@Override
	public boolean updateStock(Integer productId, Integer quantity) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ProductServiceEception(AppConstant.CATEGORY_NOT_FOUND,
						AppConstant.CATEGORY_NOT_FOUND_ERR_CD));
		product.setStock(quantity);
		productRepo.save(product);
		return true;
	}

}
