package com.piyush.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.piyush.blog.entities.User;
import com.piyush.blog.exceptions.ResourceNotFoundException;
import com.piyush.blog.payloads.UserDto;
import com.piyush.blog.repositories.RoleRepo;
import com.piyush.blog.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	RoleRepo roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUsedUser = this.userRepo.save(user);
		return this.userToDto(savedUsedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());

		User updatedUser = this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUser(int userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> userDtos = this.userRepo.findAll().stream().map(user -> this.userToDto(user))
				.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(int userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		/**
		 * user.setId(userDto.getId()); user.setName(userDto.getName());
		 * user.setEmail(userDto.getEmail()); user.setAbout(userDto.getAbout());
		 * user.setPassword(userDto.getPassword());
		 **/
		return user;
	}

	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	/**
	 * @PostConstruct public void addDemoUsers() { Role roleUser = new Role("user");
	 *                Role roleAdmin = new Role("admin");
	 *                roleRepository.save(roleUser); roleRepository.save(roleAdmin);
	 * 
	 *                Set<Role> rolesList = new HashSet<>();
	 *                rolesList.add(roleUser); User user = new User("Jaideep",
	 *                "educationmaster30@gmail.com",
	 *                this.passwordEncoder.encode("password"), rolesList);
	 *                userRepo.save(user); rolesList.add(roleAdmin); userRepo.save(
	 *                new User("Piyush", "khanna.piyush30@gmail.com",
	 *                this.passwordEncoder.encode("password"), rolesList));
	 * 
	 *                }
	 **/
}
