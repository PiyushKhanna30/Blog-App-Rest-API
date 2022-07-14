package com.piyush.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 4, message = "Name should contain more than 3 characters.")
	private String name;
	@Email(message = "Email address is invalid.")
	private String email;
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password should have minimum 3 and maximum 10 characters.")
	private String password;
	@NotEmpty
	private String about;
}
