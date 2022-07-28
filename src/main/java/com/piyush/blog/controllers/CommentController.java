package com.piyush.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piyush.blog.payloads.ApiResponse;
import com.piyush.blog.payloads.CommentDto;
import com.piyush.blog.services.CommentService;

@RestController
@RequestMapping(value = "/api")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping(value = "/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
			@PathVariable("postId") int postId) {
		return new ResponseEntity<CommentDto>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/comments/{commentId}")
	public ResponseEntity<ApiResponse> createComment(@PathVariable("commentId") int commentId) {
		commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully!", true), HttpStatus.OK);
	}
}
