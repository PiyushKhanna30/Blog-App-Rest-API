package com.piyush.blog.payloads;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
	private int id;
	@NotEmpty
	@Size(min = 3, max = 15, message = "Min 3 and maximum 15 characters allowed.")
	private String title;
	@NotEmpty
	@Size(min = 5, message = "Min 5 characters required.")
	private String content;
	private String imageName;
	private Date date;
	private CategoryDto category;
	private UserDto user;
}
