package com.shyam.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shyam.dto.ProductDto;
import com.shyam.props.AppProps;
import com.shyam.response.ApiResponse;
import com.shyam.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	@Autowired
	private AppProps props;

	@PostMapping("/addProduct")
	public ResponseEntity<ApiResponse<ProductDto>> addProduct(@RequestParam("product") String productJson,
			@RequestParam("file") MultipartFile file) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ProductDto productDto = mapper.readValue(productJson, ProductDto.class);
		Map<String, String> message = props.getMessage();
		ProductDto addedProduct = productService.addProduct(productDto, file);
		ApiResponse<ProductDto> response = new ApiResponse<>();

		if (addedProduct != null) {
			response.setStatusCode(200);
			response.setMessage(message.get("productAdded"));
			response.setData(addedProduct);
		} else {
			response.setStatusCode(500);
			response.setMessage(message.get("productAddedErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> updateProduct(@PathVariable("id") Integer productId,
			@RequestParam("product") String productJson, @RequestParam("file") MultipartFile file) throws Exception {
		Map<String, String> message = props.getMessage();
		ObjectMapper mapper = new ObjectMapper();
		ProductDto productDto = mapper.readValue(productJson, ProductDto.class);
		ProductDto updateProduct = productService.updateProduct(productId, productDto, file);

		ApiResponse<ProductDto> response = new ApiResponse<>();

		if (updateProduct != null) {
			response.setStatusCode(200);
			response.setMessage(message.get("productUpdate"));
			response.setStatusCode(200);
			response.setData(updateProduct);
		} else {
			response.setMessage(message.get("productUpdateErr"));
			response.setStatusCode(500);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts() {
		Map<String, String> message = props.getMessage();
		List<ProductDto> products = productService.getAllProducts();
		ApiResponse<List<ProductDto>> response = new ApiResponse<>();

		if (products != null) {
			response.setStatusCode(200);
			response.setMessage(message.get("productFtech"));
			response.setData(products);
		} else {
			response.setStatusCode(500);
			response.setMessage(message.get("productFtechErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable("id") Integer id){
		Map<String,String> message = props.getMessage();
		ProductDto productDto = productService.getProductById(id);
		ApiResponse<ProductDto>response=new ApiResponse<>();
		
		if(productDto!=null) {
			response.setStatusCode(200);
			response.setMessage(message.get("productSelect"));
			response.setData(productDto);
		}else {
			response.setStatusCode(500);
			response.setMessage(message.get("productSelectErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> deleteProductById(@PathVariable("id") Integer id){
		Map<String,String> message = props.getMessage();
		ProductDto productDto = productService.deleteProductById(id);
		
		ApiResponse<ProductDto> response=new  ApiResponse<>();
		
		if(productDto!=null) {
			response.setStatusCode(200);
			response.setMessage(message.get("productDelete"));
			response.setData(productDto);
			
		}else {
			response.setStatusCode(500);
			response.setMessage(message.get("productDeleteErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PatchMapping("/{id}/stock")
	public ResponseEntity<String> updateStock(@PathVariable("id") Integer id,@RequestParam("quantity") Integer quantity){
		Map<String,String> message = props.getMessage();
		boolean stock = productService.updateStock(id, quantity);
		
		if(stock) {
			return ResponseEntity.ok("Stock Updated Successfully");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Update Stock");
		}
	}
}
