package com.piyush.blog.services;

import java.util.List;

import com.piyush.blog.entities.User;
import com.piyush.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, int userId);
	UserDto getUser(int userId);
	List<UserDto> getAllUsers();
	void deleteUser(int userId);
}
