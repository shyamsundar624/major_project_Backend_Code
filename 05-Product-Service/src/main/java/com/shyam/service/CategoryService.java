package com.shyam.service;

import java.util.List;

import com.shyam.dto.CategoryDto;

public interface CategoryService {

	public CategoryDto addCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(Integer categoryId,CategoryDto categoryDto);
	
	public List<CategoryDto> getAllCategories();

	public CategoryDto getCategoryById(Integer id);

	public CategoryDto deleteCategoryById(Integer categoryId);
	
	
}
