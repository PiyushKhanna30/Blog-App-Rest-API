package com.piyush.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.piyush.blog.entities.Category;
import com.piyush.blog.entities.Post;
import com.piyush.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	Page<Post> findByCategory(Category category, Pageable pageable);

	Page<Post> findByUser(User user, Pageable pageable);

	List<Post> findByUserName(String name);

	List<Post> findByUserNameAndUserEmail(String name, String email);
}