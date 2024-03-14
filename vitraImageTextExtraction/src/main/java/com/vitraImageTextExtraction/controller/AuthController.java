package com.vitraImageTextExtraction.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitraImageTextExtraction.constants.Const_Str;
import com.vitraImageTextExtraction.entities.RegisterUser;
import com.vitraImageTextExtraction.model.LoginUserModel;
import com.vitraImageTextExtraction.model.RegisterUserModel;
import com.vitraImageTextExtraction.repository.RegisterUserRepo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(value = {"http://localhost:3000"})
public class AuthController {

	@Autowired
	RegisterUserRepo registerUserRepo;

	@GetMapping
	public Map<String, String> getUsableURLs() {
		return Map.of("register", "/register", "loginUser", "/login");
	}

	@PostMapping("register")
	public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody RegisterUserModel ru) {

		if (!ru.getPassword().equals(ru.getConfirmPassword())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(Map.of(Const_Str.Message, Const_Str.PasswordNotSame));
		}

		Optional<RegisterUser> byEmail = registerUserRepo.getByEmail(ru.getEmail());

		if (byEmail.isPresent()) {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(Map.of(Const_Str.Message, Const_Str.AlreadyExist));
		} else {
			var registerUser = new RegisterUser();
			registerUser.setEmail(ru.getEmail());
			registerUser.setPassword(ru.getPassword());
			RegisterUser save = registerUserRepo.save(registerUser);
			Map<String, Object> resp = Map.of(Const_Str.Message, Const_Str.Success, "User", save);
			return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		}
	}

	@PostMapping("login")
	public ResponseEntity<Map<String, Object>> loginUser(@Valid @RequestBody LoginUserModel lum) {
		System.out.println(lum);
		Optional<RegisterUser> existsByEmail = registerUserRepo.getByEmail(lum.getEmail());
		if (existsByEmail.isPresent()) {
			RegisterUser ru2 = existsByEmail.get();
			if (ru2.getPassword().equals(lum.getPassword()))
				return ResponseEntity.status(HttpStatus.OK).body(Map.of(Const_Str.Message, Const_Str.Success));
			else
				return ResponseEntity.status(HttpStatus.OK).body(Map.of(Const_Str.Message, Const_Str.PasswordNotSame));
		} else
			return ResponseEntity.status(HttpStatus.OK).body(Map.of(Const_Str.Message, Const_Str.NotFound));
	}

}