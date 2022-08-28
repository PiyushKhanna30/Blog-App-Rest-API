package com.piyush.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@ApiModel(description = "Data transfer object for categories.")
public class CategoryDto {
	@ApiModelProperty(notes = "Id of category.")
	private Integer id;
	@NotEmpty
	@Size(min = 4, max = 15, message = "Title should contain be more than 3 and less than equal to 15 characters.")
	@ApiModelProperty(notes = "Title of category.")
	private String title;
	@NotEmpty
	@Size(min = 10, max = 50, message = "Description should contain more than 10 and less than equal to 50 characters.")
	@ApiModelProperty(value = "Description of category.")
	private String description;
}
