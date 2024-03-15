package com.vitraImageTextExtraction.controller;

import java.io.IOException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vitraImageTextExtraction.entities.Image;
import com.vitraImageTextExtraction.repository.ImageRepository;

@RestController
@CrossOrigin(value = { "*" })
public class ImageProcessingController {

	@Autowired
	private ImageRepository imageRepository;

	@PostMapping("/img")
	public ResponseEntity uploadImage(@RequestParam MultipartFile file) {
		try {
			Image img = new Image();
			img.setName(file.getOriginalFilename());
//			img.setData(file.getBytes());
			img.setData(BlobProxy.generateProxy(file.getBytes()));
			System.out.println("saveImg: " + img);

			return new ResponseEntity<>(imageRepository.save(img), HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
