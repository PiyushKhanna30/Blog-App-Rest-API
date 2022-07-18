package com.piyush.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyush.blog.entities.Category;
import com.piyush.blog.entities.Post;
import com.piyush.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByCategory(Category category);

	List<Post> findByUser(User user);
}