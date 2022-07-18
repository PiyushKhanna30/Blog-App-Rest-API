package com.piyush.blog.controllers;

import java.util.List;

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
import com.piyush.blog.payloads.PostDto;
import com.piyush.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postService;

	@PostMapping(value = "/users/{uId}/categories/{cId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable(name = "uId") int userId,
			@PathVariable(name = "cId") int categoryId) {
		return new ResponseEntity<PostDto>(postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
	}

	@PutMapping(value = "/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "postId") int postId) {
		return new ResponseEntity<PostDto>(postService.updatePost(postDto, postId), HttpStatus.OK);
	}

	@DeleteMapping(value = "/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "postId") int postId) {
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully!", true), HttpStatus.OK);
	}

	@GetMapping(value = "/posts/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable(name = "postId") int postId) {
		return new ResponseEntity<PostDto>(postService.getPostById(postId), HttpStatus.OK);
	}

	@GetMapping(value = "/posts")
	public ResponseEntity<List<PostDto>> getPosts() {
		return new ResponseEntity<List<PostDto>>(postService.getPosts(), HttpStatus.OK);
	}

	@GetMapping(value = "/users/{uId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable(name = "uId") int userId) {
		return new ResponseEntity<List<PostDto>>(postService.getPostsByUser(userId), HttpStatus.OK);
	}

	@GetMapping(value = "/categories/{cId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(name = "cId") int categoryId) {
		return new ResponseEntity<List<PostDto>>(postService.getPostsByCategory(categoryId), HttpStatus.OK);
	}
}
