package com.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().info(new Info().title("Student Management System")
				.version("1.0").description("Api Documentation for student crud"));
	}

}
