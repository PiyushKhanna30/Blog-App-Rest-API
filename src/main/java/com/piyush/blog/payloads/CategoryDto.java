package com.piyush.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
	private Integer id;
	@NotEmpty
	@Size(min = 4, max=15, message = "Title should contain be more than 3 and less than equal to 15 characters.")
	private String title;
	@NotEmpty
	@Size(min = 10, max=50, message = "Description should contain more than 10 and less than equal to 50 characters.")
	private String description;
}
