package com.piyush.blog.services;

import java.util.List;

import com.piyush.blog.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, int userId, int categoryId);

	PostDto updatePost(PostDto postDto, int postId);

	void deletePost(int postId);

	PostDto getPostById(int postId);

	List<PostDto> getPostsByCategory(int categoryId);

	List<PostDto> getPostsByUser(int userId);

	List<PostDto> getPosts();

}
