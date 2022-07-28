package com.piyush.blog.services;

import com.piyush.blog.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto, int postId);

	void deleteComment(int commentId);
}
