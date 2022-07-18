package com.piyush.blog.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
	private int id;
	private String title;
	private String content;
	private String imageName;
	private Date date;
	private CategoryDto category;
	private UserDto user;
}
