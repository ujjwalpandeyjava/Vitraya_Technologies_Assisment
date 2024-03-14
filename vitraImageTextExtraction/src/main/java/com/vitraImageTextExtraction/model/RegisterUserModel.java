package com.vitraImageTextExtraction.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserModel {
	private int id;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;
}
