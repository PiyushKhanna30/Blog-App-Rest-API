package com.piyush.blog.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piyush.blog.entities.Comment;
import com.piyush.blog.entities.Post;
import com.piyush.blog.exceptions.ResourceNotFoundException;
import com.piyush.blog.payloads.CommentDto;
import com.piyush.blog.repositories.CommentRepo;
import com.piyush.blog.repositories.PostRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepo commentRepo;

	@Autowired
	PostRepo postRepo;

	@Autowired
	ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		Comment comment = mapper.map(commentDto, Comment.class);
		comment.setPost(post);

		comment = commentRepo.save(comment);
		return mapper.map(comment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
		commentRepo.delete(comment);
	}

}
