package com.piyush.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyush.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
