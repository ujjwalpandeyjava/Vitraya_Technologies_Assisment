package com.vitraImageTextExtraction.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.vitraImageTextExtraction.entities.RegisterUser;

@Configuration
public class BeansConfig {

	@Bean
	@Primary
	@Scope("prototype")
	public RegisterUser myBean1() {
		return new RegisterUser();
	}

}
