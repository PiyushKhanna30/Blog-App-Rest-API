package com.piyush.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.piyush.blog.payloads.ApiResponse;
import com.piyush.blog.payloads.CategoryDto;
import com.piyush.blog.services.CategoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping(value = "/")
	ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<CategoryDto>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{categoryId}")
	ResponseEntity<CategoryDto> getCategory(
			@ApiParam(value = "Id for category to retrieve", required = true) @PathVariable(name = "categoryId") Integer id) {
		return new ResponseEntity<CategoryDto>(categoryService.getCategory(id), HttpStatus.OK);
	}

	@GetMapping(value = "/")
	@ApiOperation(value = "Returns all categories", notes = "Provides us al categories to which post can be tagged.", response = CategoryDto.class)
	ResponseEntity<List<CategoryDto>> getCategories() {
		return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategories(), HttpStatus.OK);
	}

	@PutMapping(value = "/{categoryId}")
	ResponseEntity<CategoryDto> getCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable(name = "categoryId") Integer id) {
		return new ResponseEntity<CategoryDto>(categoryService.updateCategory(categoryDto, id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{categoryId}")
	ResponseEntity<ApiResponse> deleteCategory(@PathVariable(name = "categoryId") Integer id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted user successfuly.", true), HttpStatus.OK);
	}
}
