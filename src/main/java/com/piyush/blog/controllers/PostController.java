package com.piyush.blog.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.piyush.blog.payloads.ApiResponse;
import com.piyush.blog.payloads.PostDto;
import com.piyush.blog.payloads.PostResponse;
import com.piyush.blog.services.FileService;
import com.piyush.blog.services.PostService;
import com.piyush.blog.utilities.AppConstants;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.images}")
	private String path;

	@PostMapping(value = "/users/{uId}/categories/{cId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
			@PathVariable(name = "uId") int userId, @PathVariable(name = "cId") int categoryId) {
		return new ResponseEntity<PostDto>(postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
	}

	@PutMapping(value = "/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
			@PathVariable(name = "postId") int postId) {
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
	public ResponseEntity<PostResponse> getPosts(
			@RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.PAGE_NUMBER) int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.SORT_BY) String sortBy,
			@RequestParam(value = "sortDir", required = false, defaultValue = AppConstants.SORT_DIR) String sortDir) {
		return new ResponseEntity<PostResponse>(postService.getPosts(pageNumber, pageSize, sortBy, sortDir),
				HttpStatus.OK);
	}

	@GetMapping(value = "/users/{uId}/posts")
	public ResponseEntity<PostResponse> getPostsByUser(
			@RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.PAGE_NUMBER) int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.PAGE_SIZE) int pageSize,
			@PathVariable(name = "uId") int userId) {
		return new ResponseEntity<PostResponse>(postService.getPostsByUser(pageNumber, pageSize, userId),
				HttpStatus.OK);
	}

	@GetMapping(value = "/categories/{cId}/posts")
	public ResponseEntity<PostResponse> getPostsByCategory(
			@RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.PAGE_NUMBER) int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.PAGE_SIZE) int pageSize,
			@PathVariable(name = "cId") int categoryId) {
		return new ResponseEntity<PostResponse>(postService.getPostsByCategory(pageNumber, pageSize, categoryId),
				HttpStatus.OK);
	}

	@GetMapping(value = "/posts/search/{keyword}")
	public ResponseEntity<PostResponse> getPostsByTitle(
			@RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.PAGE_NUMBER) int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.PAGE_SIZE) int pageSize,
			@PathVariable(name = "keyword") String keyword) {
		return new ResponseEntity<PostResponse>(postService.searchPosts(pageNumber, pageSize, keyword), HttpStatus.OK);
	}

	@PostMapping(value = "/posts/{postId}/image")
	public ResponseEntity<PostDto> uploadImageInPost(@PathVariable(name = "postId") int postId,
			@RequestParam("image") MultipartFile image) throws IOException {
		PostDto postDto = postService.getPostById(postId);
		String fileName = fileService.uploadImage(path, image, postId);
		postDto.setImageName(fileName);
		return new ResponseEntity<PostDto>(postService.updatePost(postDto, postId), HttpStatus.OK);
	}

	@GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable("imageName") String imageName, HttpServletResponse httpServletResponse)
			throws FileNotFoundException, IOException {

		httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(fileService.getResource(path, imageName), httpServletResponse.getOutputStream());

	}
}
