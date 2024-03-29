package com.piyush.blog.services;

import java.util.List;

import com.piyush.blog.payloads.UserDto;

public interface UserService {
	UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, int userId);

	UserDto getUser(int userId);

	List<UserDto> getAllUsers();

	void deleteUser(int userId);
}
