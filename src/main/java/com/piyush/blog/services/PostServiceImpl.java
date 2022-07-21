package com.piyush.blog.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.piyush.blog.entities.Category;
import com.piyush.blog.entities.Post;
import com.piyush.blog.entities.User;
import com.piyush.blog.exceptions.ResourceNotFoundException;
import com.piyush.blog.payloads.PostDto;
import com.piyush.blog.payloads.PostResponse;
import com.piyush.blog.repositories.CategoryRepo;
import com.piyush.blog.repositories.PostRepo;
import com.piyush.blog.repositories.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		Post post = modelMapper.map(postDto, Post.class);
		post.setCategory(category);
		post.setDate(new Date());
		post.setUser(user);
		post.setImageName("default.jpg");

		Post post2 = postRepo.save(post);
		return modelMapper.map(post2, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post post2 = postRepo.save(post);
		return modelMapper.map(post2, PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		postRepo.delete(post);

	}

	@Override
	public PostResponse getPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = null;
		if (sortDir.equals("asc"))
			sort = Sort.by(sortBy).ascending();
		else
			sort = Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> postsPage = postRepo.findAll(pageable);
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postsPage.getContent().stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList()));
		postResponse.setPageNumber(postsPage.getNumber());
		postResponse.setPageSize(postsPage.getSize());
		postResponse.setTotalElements(postsPage.getTotalElements());
		postResponse.setLastPage(postsPage.isLast());
		postResponse.setTotalPages(postsPage.getTotalPages());
		return postResponse;
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getPostsByCategory(int pageNumber, int pageSize, int categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Post> postsPage = postRepo.findByCategory(category, pageable);

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postsPage.getContent().stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList()));
		postResponse.setPageNumber(postsPage.getNumber());
		postResponse.setPageSize(postsPage.getSize());
		postResponse.setTotalElements(postsPage.getTotalElements());
		postResponse.setLastPage(postsPage.isLast());
		postResponse.setTotalPages(postsPage.getTotalPages());
		return postResponse;
	}

	@Override
	public PostResponse getPostsByUser(int pageNumber, int pageSize, int userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Post> postsPage = postRepo.findByUser(user, pageable);

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postsPage.getContent().stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList()));
		postResponse.setPageNumber(postsPage.getNumber());
		postResponse.setPageSize(postsPage.getSize());
		postResponse.setTotalElements(postsPage.getTotalElements());
		postResponse.setLastPage(postsPage.isLast());
		postResponse.setTotalPages(postsPage.getTotalPages());
		return postResponse;
	}

	@Override
	public PostResponse searchPosts(int pageNumber, int pageSize, String keyword) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<Post> postsPage = postRepo.findByTitleContaining(keyword, pageable);

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postsPage.getContent().stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList()));
		postResponse.setPageNumber(postsPage.getNumber());
		postResponse.setPageSize(postsPage.getSize());
		postResponse.setTotalElements(postsPage.getTotalElements());
		postResponse.setLastPage(postsPage.isLast());
		postResponse.setTotalPages(postsPage.getTotalPages());

		searchPostsByTitle(keyword);
		return postResponse;
	}

	@Override
	public List<PostDto> searchPostsByTitle(String keyword) {
		List<PostDto> postDtos = postRepo.findByTitleContaining("%"+keyword+"%").stream()
				.map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		postDtos.stream().forEach(p -> System.err.println(p.getTitle()));
		return postDtos;
	}

}
