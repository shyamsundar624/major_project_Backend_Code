package com.shyam.mapper;

import org.modelmapper.ModelMapper;

import com.shyam.dto.CategoryDto;
import com.shyam.entity.Category;

public class CategoryMapper {

	private static final ModelMapper mapper=new ModelMapper();
	
	public static CategoryDto convertTODto(Category category) {
		return mapper.map(category, CategoryDto.class);
	}
	
	public static Category convertToEntity(CategoryDto categoryDto) {
		return mapper.map(categoryDto, Category.class);
	}
}
