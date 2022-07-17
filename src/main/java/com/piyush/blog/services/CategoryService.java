package com.piyush.blog.services;

import java.util.List;

import com.piyush.blog.payloads.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto category);

	CategoryDto updateCategory(CategoryDto category, int categoryId);

	CategoryDto getCategory(int categoryId);

	List<CategoryDto> getAllCategories();

	void deleteCategory(int categoryId);
}
