package com.piyush.blog.services;

import com.piyush.blog.payloads.PostDto;
import com.piyush.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, int userId, int categoryId);

	PostDto updatePost(PostDto postDto, int postId);

	void deletePost(int postId);

	PostDto getPostById(int postId);

	PostResponse getPostsByCategory(int pageNumber, int pageSize, int categoryId);

	PostResponse getPostsByUser(int pageNumber, int pageSize, int userId);

	PostResponse getPosts(int pageNumber, int pageSize, String sortBy, String sortDir);

}
