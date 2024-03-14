package com.vitraImageTextExtraction.controller;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vitraImageTextExtraction.entities.Image;
//import com.vitraImageTextExtraction.repository.ImageRepository;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;

@RestController
@MultipartConfig(maxFileSize = 16177215)
@CrossOrigin(value = { "http://localhost:3000" })
public class ImageProcessingController {

//	@Autowired
//	private ImageRepository imageRepository;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestBody Image payload) {
		System.out.println(payload);
		try {
			ZonedDateTime datetime = ZonedDateTime.parse(payload.get("datetime"));
			String fileBase64 = payload.get("file");

//			byte[] fileBytes = Base64.getDecoder().decode(fileBase64);

			Image image = new Image();
//			System.out.println(payload.get("file").getClass());
//			imageRepository.save(image);
			
			return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/upload_")
	public ResponseEntity<Image> uploadImage(@Valid @RequestBody Image image) {
		System.out.println(image);
		/*
		 * String base64Image = ""; try { byte[] bytes = image.getFile().getBytes();
		 * base64Image = Base64.getEncoder().encodeToString(bytes);
		 * System.out.println(base64Image); } catch (Exception e) { e.printStackTrace();
		 * }
		 * 
		 * Image savedImage = imageRepository.save(image);
		 * 
		 * // get the text from image
		 */
		return ResponseEntity.ok(new Image());
	}
}
