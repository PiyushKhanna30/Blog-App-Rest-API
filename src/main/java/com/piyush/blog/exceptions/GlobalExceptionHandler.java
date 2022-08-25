package com.piyush.blog.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.piyush.blog.payloads.ApiResponse;
import com.piyush.blog.utilities.AppConstants;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(APIException.class)
	public ResponseEntity<ApiResponse> imageNotFoundException(APIException ex) {
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> message = new HashMap<>();
		ex.getBindingResult().getFieldErrors().stream().forEach((error) -> {
			message.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<Map<String, String>>(message, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<ApiResponse> fileNotFoundException(FileNotFoundException ex) {
		String message = AppConstants.FNF_MESSAGE;
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ApiResponse> ioException(IOException ex) {
		String message = AppConstants.IO_MESSAGE;
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse> accessDeniedException(AccessDeniedException ex) {
		String message = AppConstants.ACD_MESSAGE;
		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.FORBIDDEN);
	}
}
