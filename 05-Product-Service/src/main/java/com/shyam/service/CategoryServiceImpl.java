package com.shyam.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.constant.AppConstant;
import com.shyam.dto.CategoryDto;
import com.shyam.entity.Category;
import com.shyam.exception.ProductServiceEception;
import com.shyam.mapper.CategoryMapper;
import com.shyam.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = CategoryMapper.convertToEntity(categoryDto);
		Category saveCategory = categoryRepo.save(category);
		return CategoryMapper.convertTODto(saveCategory);
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
		Category existingCategory = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ProductServiceEception(AppConstant.CATEGORY_NOT_FOUND,
						AppConstant.CATEGORY_NOT_FOUND_ERR_CD));

		existingCategory.setCategoryName(categoryDto.getCategoryName());
		Category updateCategory = categoryRepo.save(existingCategory);
		return CategoryMapper.convertTODto(updateCategory);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepo.findAll();
		return categories.stream().map(CategoryMapper::convertTODto).collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Category category = categoryRepo.findById(id)
				.orElseThrow(() -> new ProductServiceEception(AppConstant.CATEGORY_NOT_FOUND,
						AppConstant.CATEGORY_NOT_FOUND_ERR_CD));
		return CategoryMapper.convertTODto(category);
	}

	@Override
	public CategoryDto deleteCategoryById(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ProductServiceEception(AppConstant.CATEGORY_NOT_FOUND,
						AppConstant.CATEGORY_NOT_FOUND_ERR_CD));
		categoryRepo.delete(category);
		return CategoryMapper.convertTODto(category);
	}

}
