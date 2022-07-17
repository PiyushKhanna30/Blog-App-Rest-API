package com.piyush.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyush.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
