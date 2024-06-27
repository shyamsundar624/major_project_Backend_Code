package com.shyam.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.dto.CategoryDto;
import com.shyam.props.AppProps;
import com.shyam.response.ApiResponse;
import com.shyam.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AppProps props;

	@PostMapping
	public ResponseEntity<ApiResponse<CategoryDto>> addCategory(@RequestBody CategoryDto categoryDto) {
		Map<String, String> message = props.getMessage();
		CategoryDto addedCategory = categoryService.addCategory(categoryDto);
		ApiResponse<CategoryDto> response = new ApiResponse<>();

		if (addedCategory != null) {
			response.setStatusCode(200);
			response.setMessage(message.get("categoryAdded"));
			response.setData(addedCategory);
		} else {
			response.setStatusCode(500);
			response.setMessage(message.get("categoryAddErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@PathVariable("id") Integer categoryId,
			@RequestBody CategoryDto categoryDto) {
		Map<String, String> message = props.getMessage();
		CategoryDto updateCategory = categoryService.updateCategory(categoryId, categoryDto);
		ApiResponse<CategoryDto> response = new ApiResponse<>();

		if (updateCategory != null) {
			response.setStatusCode(200);
			response.setMessage(message.get("categoryUpdate"));
			response.setData(updateCategory);
		} else {
			response.setStatusCode(500);
			response.setMessage(message.get("categoryUpdateErr"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategories(){
		Map<String,String> message = props.getMessage();
		List<CategoryDto> allCategories = categoryService.getAllCategories();
		ApiResponse<List<CategoryDto>> response=new ApiResponse<>();
		
		if(allCategories!=null) {
			response.setStatusCode(200);
			response.setMessage(message.get("categoryFetch"));
			response.setData(allCategories);
		}else {
			response.setStatusCode(500);
			response.setMessage(message.get("categoryFetchErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> getCategoryById(@PathVariable("id") Integer categoryId){
		Map<String,String> message = props.getMessage();
		CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
		ApiResponse<CategoryDto> response=new ApiResponse<>();
		
		if(categoryDto!=null) {
			response.setStatusCode(200);
			response.setMessage(message.get("categorySelect"));
			response.setData(categoryDto);
		}else {
			response.setStatusCode(500);
			response.setMessage(message.get("categorySelectErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> deleteCategoryById(@PathVariable("id") Integer categoryId){
		Map<String,String> message = props.getMessage();
		CategoryDto categoryDto = categoryService.deleteCategoryById(categoryId);
		ApiResponse<CategoryDto> response=new ApiResponse<>();
		if(categoryDto!=null) {
			response.setStatusCode(200);
			response.setMessage(message.get("categoryDelete"));
			response.setData(categoryDto);
		}else {
			response.setStatusCode(500);
			response.setMessage(message.get("categoryDeleteErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
