package com.vitraImageTextExtraction.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserModel {
	@NotBlank
	private String email;
	@NotBlank
	private String password;
}