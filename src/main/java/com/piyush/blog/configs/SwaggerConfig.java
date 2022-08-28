package com.piyush.blog.configs;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select()
//				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.piyush")).build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Blog-API", "Blog application documentation", "1.0", "Free to use",
				new Contact("Piyush Khanna", "piyushkhanna30.github.io", "khanna.piyush30@gmail.com"), "API Licence",
				"piyushkhanna30.github.io", Collections.emptyList());
	}

}
