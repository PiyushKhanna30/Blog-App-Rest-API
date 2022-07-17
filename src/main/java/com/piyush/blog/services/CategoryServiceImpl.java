package com.piyush.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piyush.blog.entities.Category;
import com.piyush.blog.exceptions.ResourceNotFoundException;
import com.piyush.blog.payloads.CategoryDto;
import com.piyush.blog.repositories.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto category) {
		Category category2 = categoryRepo.save(modelMapper.map(category, Category.class));
		return modelMapper.map(category2, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, int categoryId) {
		Category category2 = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		category2.setTitle(category.getTitle());
		category2.setDescription(category.getDescription());

		Category category3 = categoryRepo.save(modelMapper.map(category2, Category.class));
		return modelMapper.map(category3, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<CategoryDto> categoryDtos = categoryRepo.findAll().stream()
				.map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		categoryRepo.delete(category);
	}

}
